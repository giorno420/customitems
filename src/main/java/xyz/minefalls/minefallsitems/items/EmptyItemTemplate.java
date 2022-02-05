package xyz.minefalls.minefallsitems.items;

import org.bukkit.Material;
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

import java.util.List;

public class EmptyItemTemplate extends CustomItem {
    public EmptyItemTemplate(Material material, String name, Rarity rarity, boolean stackable, boolean oneTimeUse, boolean hasActiveEffect, List<Ability> abilities, boolean placeable, String id) {
        super(material, name, rarity, stackable, oneTimeUse, hasActiveEffect, abilities, placeable, id);
    }

    @Override
    public void getSpecificLorePrefix(List<String> lore, ItemStack item) {

    }

    @Override
    public void getSpecificLoreSuffix(List<String> lore, ItemStack item) {

    }

    @Override
    public void onItemStackCreate(ItemStack item) {

    }

    @Override
    public boolean leftClickAirAction(Player player, ItemStack item) {
        return false;
    }

    @Override
    public boolean leftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) {
        return false;
    }

    @Override
    public boolean rightClickAirAction(Player player, ItemStack item) {
        return false;
    }

    @Override
    public boolean rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) {
        return false;
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
