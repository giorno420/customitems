package xyz.minefalls.minefallsitems;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.minefalls.minefallsitems.utils.Ability;
import xyz.minefalls.minefallsitems.utils.Rarity;
import xyz.minefalls.minefallsitems.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomItem {

    private ItemStack item;
    private String name;
    private Rarity rarity;
    private boolean stackable;
    private boolean oneTimeUse;
    private boolean hasActive;
    private List<Ability> abilities;
    private boolean placeable;
    private String id;
    // TODO: add crafting recipe
    // TODO: create a custom crafting table thingy like hypixel skyblock does

    public CustomItem(Material material, String name, Rarity rarity, boolean stackable, boolean oneTimeUse, boolean hasActiveEffect, List<Ability> abilities, boolean placeable, String id) {
        this.item = new ItemStack(material);
        this.name = name;
        this.rarity = rarity;
        this.stackable = stackable;
        this.oneTimeUse = oneTimeUse;
        this.hasActive = hasActiveEffect;
        this.abilities = abilities;
        this.placeable = placeable;
        this.id = id;
    }

    public CustomItem(ItemStack item, String name, Rarity rarity, boolean stackable, boolean oneTimeUse, boolean hasActiveEffect, List<Ability> abilities, boolean placeable, String id) {
        this.item = item;
        this.name = name;
        this.rarity = rarity;
        this.stackable = stackable;
        this.oneTimeUse = oneTimeUse;
        this.hasActive = hasActiveEffect;
        this.abilities = abilities;
        this.placeable = placeable;
        this.id = id;
    }

    public ItemStack createItem(int amount){
        ItemStack newItem = this.item.clone();
        ItemMeta meta = newItem.getItemMeta();

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        newItem.setItemMeta(meta);

        Utilities.nameItem(newItem, this.rarity.getColor() + this.name);
        Utilities.loreItem(newItem, this.getLore(newItem));
        Utilities.storeStringInItem(newItem, "rarity", this.rarity.toString().toLowerCase());
        this.onItemStackCreate(newItem);

        return newItem;
    }

    public static void destroy(ItemStack item, int quantity){
        if (item.getAmount() <= quantity) {
            item.setAmount(0);
        }
        else {
            item.setAmount(item.getAmount() - quantity);
        }
    }

    /**
     * Creates an ArrayList for the lore of this item
     * @param item
     *   The ItemStack to create the lore of
     * @return
     *   The ArrayList object of the lore to add
     */
    private List<String> getLore(ItemStack item) {
        List<String> lore = new ArrayList<>();

        this.getSpecificLorePrefix(lore, item);
        lore.add("");

        for (Ability ability : this.abilities) {
            lore.addAll(ability.toLore());
            lore.add("");
        }


        this.getSpecificLoreSuffix(lore, item);
        if (this.oneTimeUse) {
            lore.add(ChatColor.DARK_GRAY + "One time use only.");
        }

        lore.add("" + this.rarity.getColor() + ChatColor.BOLD + this.rarity.toString());
        return lore;
    }

    public void onItemUse(Player player, ItemStack item){
        if (this.oneTimeUse && player.getGameMode() != GameMode.CREATIVE){
            destroy(item, 1);
        }
    }


    public abstract void getSpecificLorePrefix(List<String> lore, ItemStack item);

    public abstract void getSpecificLoreSuffix(List<String> lore, ItemStack item);

    /** When the item is created */
    public abstract void onItemStackCreate(ItemStack item);

    /** Left clicking air with the item */
    public abstract boolean leftClickAirAction(Player player, ItemStack item);

    /** Left clicking a block with the item */
    public abstract boolean leftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item);

    /** Right clicking air with the item */
    public abstract boolean rightClickAirAction(Player player, ItemStack item);

    /** Right clicking a block with the item */
    public abstract boolean rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item);

    /** Middle clicking with the item*/
    public abstract boolean middleClickAction(Player player, ItemStack item);

    /** Hitting an entity with the item */
    public abstract boolean hitEntityAction(Player player, EntityDamageByEntityEvent event, Entity damagedEntity, ItemStack item);

    /** When you break a block using said item*/
    public abstract boolean breakBlockAction(Player player, BlockBreakEvent event, Block block, ItemStack item);

    /** When holding the item triggers an active effect */
    public abstract boolean activeEffect(Player player, ItemStack item);


}
