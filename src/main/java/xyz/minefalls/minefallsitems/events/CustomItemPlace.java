package xyz.minefalls.minefallsitems.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import xyz.minefalls.minefallsitems.utils.Utilities;

public class CustomItemPlace implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (Utilities.isCustomItem(event.getItemInHand())) {
            event.setCancelled(true);
        }
    }
}
