package xyz.minefalls.minefallsitems.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.minefalls.minefallsitems.CustomItem;
import xyz.minefalls.minefallsitems.utils.Utilities;

public class CustomItemUse implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (Utilities.isCustomItem(item)){
            CustomItem custom = Utilities.getCustom(item);
            if (custom.breakBlockAction(player, event, event.getBlock(), item)){
                custom.onItemUse(player, item);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        CustomItem custom = Utilities.getCustom(item);
        if (custom != null) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                if (!player.isSneaking()) {
                    if (custom.leftClickAirAction(player, item)) {
                        custom.onItemUse(player, item);
                    }
                }
            }
                 else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (!player.isSneaking()) {
                    if (custom.leftClickBlockAction(player, event, event.getClickedBlock(), item)) {
                        custom.onItemUse(player, item);
                    }
                }
            }
            else if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (!player.isSneaking()) {
                    if (custom.rightClickAirAction(player, item)) {
                        custom.onItemUse(player, item);
                    }
                }
            }
            else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (!player.isSneaking()) {
                    if (custom.rightClickBlockAction(player, event, event.getClickedBlock(), item)) {
                        custom.onItemUse(player, item);
                    }
                }
            }

        }

    }
}
