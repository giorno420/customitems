package xyz.minefalls.minefallsitems.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import xyz.minefalls.minefallsitems.CustomItem;
import xyz.minefalls.minefallsitems.Main;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Utilities {

    /**
     * Names an item
     * @param item
     *   The ItemStack to name
     * @param name
     *   The name to give the ItemStack
     * @return
     *   The updated named ItemStack
     */
    public static ItemStack nameItem(ItemStack item, String name){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        return item;
    }

    /**
     * Names an item
     * @param item
     *   The Material to name
     * @param name
     *   The name to give the ItemStack
     * @return
     *   The updated named ItemStack
     */
    public static ItemStack nameItem(Material item, String name){
        return nameItem(new ItemStack(item), name);
    }

    /**
     * i don't even know anymore
     */
    public static List<String> stringToLore(String string, int characterLimit, ChatColor prefixColor) {
        String[] words = string.split(" ");
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();
        int worldLength = words.length;

        for (String word : words) {
            if (!word.equals("/newline")) {
                if (currentLine.toString().equals("")) {
                    currentLine = new StringBuilder(word);
                } else {
                    currentLine.append(" ").append(word);
                }
            }

            if (word.equals("/newline") || currentLine.length() + word.length() >= characterLimit) {
                String newLine = currentLine.toString();
                lines.add("" + prefixColor + newLine);
                currentLine = new StringBuilder();
            }
        }

        if (currentLine.length() > 0) {
            lines.add("" + prefixColor + currentLine);
        }

        return lines;
    }

    /**
     * Adds lore to a specific item so I don't have to rewrite the same shitty lines over and over again
     * @param item
     *   The ItemStack to give the lore to
     * @param lore
     *   The List of the lore
     * @return
     *   The ItemStack, with the lore
     */
    public static ItemStack loreItem(ItemStack item, List<String> lore){
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    /**
     * Checks if the given item is a custom item
     * @param item
     *   ItemStack to check
     * @return
     *   True if the item is custom. False if it isn't
     */
    public static boolean isCustomItem(ItemStack item){
        if (item == null){
            return false;
        }
        else if (!item.hasItemMeta()){
            return false;
        }
        else {
            Collection<CustomItem> allItems = Main.getAllItems();
            while (allItems.iterator().hasNext()) {
                for (CustomItem i : allItems) {
                    if (i.createItem(item.getAmount()).equals(item)){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Gets the CustomItem instance from an ItemStack.
     * @param item
     *   The ItemStack
     * @return
     *   The CustomItem instance of said ItemStack. null if it doesn't exist
     */
    public static CustomItem getCustom(ItemStack item){
        Collection<CustomItem> allItems = Main.getAllItems();
        for (CustomItem i : allItems) {
            if (i.createItem(item.getAmount()).equals(item)){
                return i;
            }
        }
        return null;
    }

    /**
     * Stores data in an item
     * @param item
     *   The item to store the data in
     * @param key
     *   The key of the data
     * @param value
     *   The value that the key contains
     */
    public static void storeStringInItem(ItemStack item, String key, String value){
        NamespacedKey k = new NamespacedKey(Main.getInstance(), key);

        if (item != null){
            if (item.hasItemMeta()){
                ItemMeta meta = item.getItemMeta();
                meta.getPersistentDataContainer().set(k, PersistentDataType.STRING, value);
                item.setItemMeta(meta);
            }
        }
    }

    /**
     * Stores data in an item
     * @param item
     *   The item to store the data in
     * @param key
     *   The key of the data
     * @param value
     *   The value that the key contains
     */
    public static void storeIntInItem(ItemStack item, String key, int value){
        NamespacedKey k = new NamespacedKey(Main.getInstance(), key);

        if (item != null){
            if (item.hasItemMeta()){
                ItemMeta meta = item.getItemMeta();
                meta.getPersistentDataContainer().set(k, PersistentDataType.INTEGER, value);
                item.setItemMeta(meta);
            }
        }
    }

    /**
     * Returns stored data with a specific key
     * @param item
     *   The item in which the data is stored
     * @param key
     *   The key to find the data
     * @return
     *   null if doesn't exist. data string if it does.
     */
    public static String getDataFromItem(ItemStack item, String key){
        NamespacedKey k = new NamespacedKey(Main.getInstance(), key);

        if (item == null){
            return null;
        }
        else if (!item.hasItemMeta()){
            return null;
        }
        else {
            PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
            return container.has(k, PersistentDataType.STRING) ? container.get(k, PersistentDataType.STRING) : null;
        }
    }

    /**
     * Returns stored data with a specific key
     * @param item
     *   The item in which the data is stored
     * @param key
     *   The key to find the data
     * @return
     *   null if doesn't exist. data string if it does.
     */
    public static Integer getIntFromItem(ItemStack item, String key){
        NamespacedKey k = new NamespacedKey(Main.getInstance(), key);

        if (item == null){
            return null;
        }
        else if (!item.hasItemMeta()){
            return null;
        }
        else {
            PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
            return container.has(k, PersistentDataType.INTEGER) ? container.get(k, PersistentDataType.INTEGER) : null;
        }
    }

    public static boolean enforceCooldown(Player player, String key, double seconds, ItemStack item, boolean throwError) {
        double time = (double) System.currentTimeMillis() / 1000.0D;
        int lastTime;
        try {
            lastTime = getIntFromItem(item, key);
        }
        catch (NullPointerException error){
            storeIntInItem(item, key, 0);
        }
        finally {
            lastTime = getIntFromItem(item, key);
        }

        if (lastTime == 0) {
            storeIntInItem(item, key, (int) time);
            return false;
        }
        else if (time - seconds > (double) lastTime) {
            storeIntInItem(item, key, (int) time);
            return false;
        }
        else {
            int timeLeft = (int)time - lastTime;
            timeLeft = (int)seconds - timeLeft;
            if (throwError) {
                player.sendRawMessage(ChatColor.RED + "This ability is on cooldown for " + timeLeft + "s.");
                player.playSound(player.getLocation(), Sound.ITEM_BOTTLE_FILL_DRAGONBREATH, 1, 1);
            }

            return true;
        }
    }

}
