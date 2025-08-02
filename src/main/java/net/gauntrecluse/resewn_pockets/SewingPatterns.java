package net.gauntrecluse.resewn_pockets;

import net.gauntrecluse.resewn_pockets.mixin.InventoryMixin;
import net.gauntrecluse.resewn_pockets.mixin.ItemStackMixin;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * Class that uses the mod's config and freely encourages external mods' mixins to modify
 * the conditions applied to players picking up items. <br>
 * This class's methods are used by Mixins to add to the vanilla item logic, such that the overall logic will prevent
 * the player from getting the item if either Resewn Pockets or Vanilla checks fail.
 * @author GauntRecluse
 * @since initial development
 */
public class SewingPatterns {
    /**
     * Contains the logic for whether the mod should allow a player to pick up an item on the ground or not. <br>
     * Injects its logic in {@link Inventory#add(int, ItemStack)} via {@link InventoryMixin}.
     * @param player The player picking up the item, can be used to get inventory and its contents
     * @param itemStack The item possibly being picked up
     * @return false when conditions aren't met
     */
    public static boolean canPickUp(Player player, ItemStack itemStack) {
        //TODO: Remove the loggers once debugging is complete
        ResewnPockets.LOGGER.debug("canPickUp triggered with parameters {} and {}", player, itemStack);
        ResewnPockets.LOGGER.warn("canPickUp currently always returns true");
        return true;
    }

    /**
     * Contains the logic for whether the mod should allow a player to hold onto an item or not.
     * @param itemStack The ItemStack that is currently being ticked by {@link Inventory#tick()}, then sent here by {@link ItemStackMixin}
     * @param playerEntity The entity belonging to the inventory.
     * @return false when conditions aren't met, causing the player to drop the item as if they threw it.
     */
    public static boolean mayHold(ItemStack itemStack, ServerPlayer playerEntity) {
        ResewnPockets.LOGGER.debug("mayPut triggered.");

        ResewnPockets.LOGGER.warn("mayPut currently always returns false");
        return false;

    }
}

