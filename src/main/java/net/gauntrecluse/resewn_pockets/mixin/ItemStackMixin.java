package net.gauntrecluse.resewn_pockets.mixin;


import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.gauntrecluse.resewn_pockets.util.SewingPatterns;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Debug(export = false)
@Mixin(value = ItemStack.class)
public abstract class ItemStackMixin implements DataComponentHolder, FabricItemStack {


    /**
     * This adds a check that makes the player drop an item if the logic of {@link SewingPatterns#mayHold(ItemStack, ServerPlayer)} returns false. <br>
     * It runs with every inventory tick, and works serverside.
     */
    @Inject(
            method = "inventoryTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;",
                    ordinal = 1
            ), cancellable = true
    )
    private void inventoryTickInjectSew(Level level, Entity entity, int i, boolean bl, CallbackInfo ci) {

        if(entity instanceof ServerPlayer){
            if(!SewingPatterns.mayHold((ItemStack)(Object)this, (ServerPlayer)entity)) {
            ((ServerPlayer) entity).drop((ItemStack)(Object)this, false, true);
            ((ServerPlayer) entity).getInventory().removeItem((ItemStack)(Object)this);
            ci.cancel();
            }
        }
    }
}
