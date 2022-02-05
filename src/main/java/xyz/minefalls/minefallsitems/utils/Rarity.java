package xyz.minefalls.minefallsitems.utils;

import org.bukkit.ChatColor;

public enum Rarity {

    COMMON(ChatColor.WHITE),
    UNCOMMON(ChatColor.GREEN),
    RARE(ChatColor.BLUE),
    EPIC(ChatColor.DARK_PURPLE),
    LEGENDARY(ChatColor.GOLD),
    MYTHIC(ChatColor.LIGHT_PURPLE),
    SPECIAL(ChatColor.RED),
    UNFINISHED(ChatColor.DARK_RED);

    private ChatColor color;

    private Rarity(ChatColor color){
        this.color = color;
    }

    public ChatColor getColor() {
        return color;
    }

    public boolean isRarerThan(Rarity rarity){
        int current = this.getIndex();
        int param = rarity.getIndex();

        return current > param;
    }

    private int getIndex(){
        int index = 0;
        Rarity[] values = values();
        int valueLength = values.length;

        for (int i = 0; i < valueLength; i++){
            Rarity rarity = values[i];
            if (this.equals(rarity)){
                return index;
            }

            index++;
        }

        return -1;
    }
}
