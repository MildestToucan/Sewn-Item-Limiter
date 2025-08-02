package net.gauntrecluse.resewn_pockets.mixin;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.gauntrecluse.resewn_pockets.SewingPatterns;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

/**
 * This Mixin uses the {@link SewingPatterns} class for its logic. <br>
 * in order to modify what this mixin does, you may either mix into the SewingPatterns class, or use the mod's config. <br>
 * @author GauntRecluse
 * @since initial development
 */
@Debug(export = false) //TODO: Switch to false then remove this comment before building.
@Mixin(value = Inventory.class)
public abstract class InventoryMixin {
    @Shadow @Final public Player player;

    @WrapOperation(
            method = "add(ILnet/minecraft/world/item/ItemStack;)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z", ordinal = 0))
    private boolean inventoryAddSew(ItemStack itemStack, Operation<Boolean> original) {
        return original.call(itemStack) || !SewingPatterns.canPickUp(this.player, itemStack);
    }
}



