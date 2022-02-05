package xyz.minefalls.minefallsitems.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.minefalls.minefallsitems.CustomItem;
import xyz.minefalls.minefallsitems.utils.Ability;
import xyz.minefalls.minefallsitems.utils.Rarity;

import java.util.List;

public class Cocaine extends CustomItem {

    public Cocaine(Material material, String name, Rarity rarity, boolean stackable, boolean oneTimeUse, boolean hasActiveEffect, List<Ability> abilities, boolean placeable, String id) {
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
        rightClick(player);
        return true;
    }

    @Override
    public boolean rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) {
        rightClick(player);
        return true;
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

    private void rightClick(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000000, 50, true, false));
        player.sendRawMessage(ChatColor.GREEN + "The drugs have been used.");
        player.playSound(player.getLocation(), Sound.ITEM_BOTTLE_FILL_DRAGONBREATH, 1F, 1F);
    }
}
