package xyz.minefalls.minefallsitems.items;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.minefalls.minefallsitems.CustomItem;
import xyz.minefalls.minefallsitems.utils.Ability;
import xyz.minefalls.minefallsitems.utils.Rarity;
import xyz.minefalls.minefallsitems.utils.Utilities;

import java.util.HashMap;
import java.util.List;

public class LightningRod extends CustomItem {

    public LightningRod(Material material, String name, Rarity rarity, boolean stackable, boolean oneTimeUse, boolean hasActiveEffect, List<Ability> abilities, boolean placeable, String id) {
        super(material, name, rarity, stackable, oneTimeUse, hasActiveEffect, abilities, placeable, id);
    }

    @Override
    public void getSpecificLorePrefix(List<String> lore, ItemStack item) {
    }

    @Override
    public void getSpecificLoreSuffix(List<String> lore, ItemStack item) {
        lore.add(ChatColor.GRAY + "Be careful, for with great power, comes great ");
        lore.add(ChatColor.GRAY + "power hungry people who want to steal your stuff");
        lore.add(" ");
    }

    @Override
    public void onItemStackCreate(ItemStack item) {

    }

    @Override
    public boolean leftClickAirAction(Player player, ItemStack item) {
        Location location = player.getTargetBlockExact(100).getLocation();
        location.getWorld().strikeLightning(location);
        player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, 2);

        return true;
    }

    @Override
    public boolean leftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) {
        return this.leftClickAirAction(player, item);
    }

    @Override
    public boolean rightClickAirAction(Player player, ItemStack item) {
        if (Utilities.enforceCooldown(player, "lightning", 2D, item, true)) {
            return false;
        }
        else {
            Location loc = player.getTargetBlockExact(50).getLocation();
            loc.getWorld().createExplosion(loc, 6F);
            return true;
        }

    }

    @Override
    public boolean rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) {
        return this.rightClickAirAction(player, item);
    }

    @Override
    public boolean middleClickAction(Player player, ItemStack item) {
        return false;
    }

    @Override
    public boolean hitEntityAction(Player player, EntityDamageByEntityEvent event, Entity damagedEntity, ItemStack item) {
        return false;
    }

    @Override
    public boolean breakBlockAction(Player player, BlockBreakEvent event, Block block, ItemStack item) {
        return false;
    }

    @Override
    public boolean activeEffect(Player player, ItemStack item) {
        return false;
    }
}
