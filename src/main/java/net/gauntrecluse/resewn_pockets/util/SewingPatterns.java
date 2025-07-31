package net.gauntrecluse.resewn_pockets.util;

import net.gauntrecluse.resewn_pockets.ResewnPockets;
import net.gauntrecluse.resewn_pockets.mixin.InventoryMixin; //Yes, those imports exist just for the Javadoc links.
import net.gauntrecluse.resewn_pockets.mixin.SlotSewingMixin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * Class that uses the mod's config and freely encourages external mods' mixins to modify
 * the conditions applied to players picking up items. <br>
 * Specifically, this is referenced by the {@link InventoryMixin}
 * class and the {@link SlotSewingMixin} class. {@link InventoryMixin} is at priority 500, this means if you wish to modify the conditions here via mixins,
 * you must use a mixin whose priority value is greater than 500.
 * @author GauntRecluse
 * @since initial development
 */
public class SewingPatterns {
    public static boolean canPickUp(Player player, ItemStack itemStack) {
        //TODO: Remove the loggers once debugging is complete
        ResewnPockets.LOGGER.info("canPickUp triggered with parameters {} and {}", player, itemStack);
        ResewnPockets.LOGGER.warn("canPickUp triggered, always returns false currently");
        return false;
    }

    //Logical structure to be implemented into canPickUp or in some other fashion once debugging gets to that stage.
    public static boolean logicDraft(Player player, ItemStack itemStack) {
        Iterable<ItemStack> playerSlots = player.getAllSlots(); //May need to figure out if there isn't a way to not calculate this every single check?

    }
}

