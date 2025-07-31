package net.gauntrecluse.resewn_pockets.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.gauntrecluse.resewn_pockets.util.SewingPatterns;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;


/**
 * See Javadocs at {@link InventoryMixin} and {@link SewingPatterns}
 * @author GauntRecluse
 * @implNote
 */
@Mixin(value = Slot.class)
public abstract class SlotSewingMixin {

    @Shadow public abstract ItemStack getItem();


    //TODO: Test whether this works for all given inventories/containers, even modded ones.
    /*
    * Currently tested:
    * Creative Menu
    * */
    @WrapMethod(method = "mayPickup")
    private boolean stub(Player player, Operation<Boolean> original) {
        return SewingPatterns.canPickUp(player, this.getItem()) && original.call(player);
    }


}
