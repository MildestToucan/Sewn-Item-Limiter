package net.gauntrecluse.resewn_pockets.mixin;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.gauntrecluse.resewn_pockets.util.SewingPatterns;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * This Mixin uses the {@link net.gauntrecluse.resewn_pockets.util.SewingPatterns} class for its logic. <br>
 * in order to modify what this mixin does, you may either mix into the SewingPatterns class, or use the mod's config. <br>
 * This Mixin has a priority of 500 (default for mixins is 1000)
 * to make sure it will most likely load after anything trying to modify its behavior.
 * @see SlotSewingMixin
 * @author GauntRecluse
 * @since initial development
 */
@Debug(export = true) //TODO: Switch to false then remove this comment before building.
@Mixin(value = Inventory.class, priority = 500)
public abstract class InventoryMixin {
    @Shadow @Final public Player player;

    //Mixin affecting item pickups from the ground, works purely Serverside.
    @WrapOperation(
            method = "add(ILnet/minecraft/world/item/ItemStack;)Z",
            at = @At(value = "INVOKE:FIRST", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z"))
    private boolean inventoryAddConditionSew(ItemStack itemStack, Operation<Boolean> original) {

        return original.call(itemStack) || !SewingPatterns.canPickUp(this.player, itemStack);
    }

    //Not sure if this really even affects anything?
    //TODO: Actually test this.
    @Inject(method = "placeItemBackInInventory(Lnet/minecraft/world/item/ItemStack;Z)V", at = @At(value = "HEAD"))
    private void inventoryPlaceItemSew(ItemStack itemStack, boolean bl, CallbackInfo ci) {
        if(!SewingPatterns.canPickUp(this.player, itemStack)) {
            this.player.drop(itemStack, false);
            return;
        }
    }
}



