package xyz.minefalls.minefallsitems.utils;

import lombok.Getter;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Ability {

    @Getter private String name;
    @Getter private String description;
    @Getter private AbilityType type;
    private int cooldown = 0;

    /**
     * Constructor method
     * @param name
     *   Name of the ability (eg: Instant Combustion)
     * @param type
     *   AbilityType of the ability (left click, right click, middle click, none)
     * @param description
     *   Description of the ability (eg: Makes you combust rapidly)
     */
    public Ability(String name, AbilityType type, String description){
        this.name = name;
        this.type = type;
        this.description = description;
    }
    /**
     * Constructor method
     * @param name
     *   Name of the ability (eg: Instant Combustion)
     * @param type
     *   AbilityType of the ability (left click, right click, middle click, none)
     * @param description
     *   Description of the ability (eg: Makes you combust rapidly)
     * @param cooldown
     *   The cooldown of the ability (Don't use if you don't want a cooldown)
     */
    public Ability(String name, AbilityType type, String description, int cooldown){
        this.name = name;
        this.type = type;
        this.description = description;
        this.cooldown = cooldown;
    }

    /**
     * Takes in the information and turns it into lore
     * @return lore
     *   The ArrayList object of the lore
     */
    public List<String> toLore() {
        List<String> lore = new ArrayList<>();
        if (this.type != AbilityType.FULL_SET_BONUS) {
            lore.add(ChatColor.GOLD + "Item Ability: " + this.name + " " + ChatColor.YELLOW + ChatColor.BOLD + this.type.getText());
            lore.addAll(Utilities.stringToLore(this.description, 40, ChatColor.GRAY));
            if (this.cooldown > 0) {
                lore.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + this.cooldown + "s.");
            }
        }
        else {
            lore.add(ChatColor.GOLD + "Full Set Bonus: " + this.name + " " + ChatColor.YELLOW);
            lore.addAll(Utilities.stringToLore(this.description, 40, ChatColor.GRAY));
            if (this.cooldown > 0) {
                lore.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + this.cooldown + "s.");
            }
        }

        return lore;
    }
}
