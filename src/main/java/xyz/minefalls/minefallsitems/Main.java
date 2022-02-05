package xyz.minefalls.minefallsitems;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.minefalls.minefallsitems.commands.CustomItemCommand;
import xyz.minefalls.minefallsitems.events.CustomItemPlace;
import xyz.minefalls.minefallsitems.events.CustomItemUse;
import xyz.minefalls.minefallsitems.items.Cocaine;
import xyz.minefalls.minefallsitems.items.EmptyItemTemplate;
import xyz.minefalls.minefallsitems.items.LightningRod;
import xyz.minefalls.minefallsitems.utils.Ability;
import xyz.minefalls.minefallsitems.utils.AbilityType;
import xyz.minefalls.minefallsitems.utils.Rarity;

import java.util.*;

public final class Main extends JavaPlugin {

    private static Map<String, CustomItem> items = new HashMap<>();
    @Getter public static Main instance;

    @Override
    public void onEnable() {
        PluginManager pm = this.getServer().getPluginManager();
        instance = this;
        registerShit();
        this.getCommand("customitem").setExecutor(new CustomItemCommand());
        pm.registerEvents(new CustomItemUse(), this);
        pm.registerEvents(new CustomItemPlace(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public void registerShit(){

        putItem("null", new EmptyItemTemplate(
                Material.BARRIER,
                "null",
                Rarity.UNFINISHED,
                true,
                false,
                false,
                Collections.singletonList(
                        new Ability(
                                "What?",
                                AbilityType.NONE,
                                "If you see this item instead of the item you wanted, you messed something up"
                        )
                ),
                false,
                "null"
                )
        );
        putItem("cocaine",
                new Cocaine(
                    Material.SUGAR,
                    "Cocaine", Rarity.COMMON,
                    true,
                    true,
                    false,
                    Collections.singletonList(
                            new Ability(
                                    "Drugs",
                                    AbilityType.RIGHT_CLICK,
                                    "Gets you high instantly. Loses effect once dead"
                            )
                    ),
                    false,
                    "cocaine"
                )
        );
        putItem("lightning_rod", new LightningRod(
                        Material.BLAZE_ROD,
                        "Lightning Rod", Rarity.MYTHIC,
                        false,
                        false,
                        false,
                        Arrays.asList(
                                new Ability(
                                        "Thunder",
                                        AbilityType.RIGHT_CLICK,
                                        "Summons a lightning bolt at the block you're looking at. Maximum range of 100 blocks"
                                )
                        ),
                        false,
                        "lightning_rod"
                )
        );
    }
    public static void putItem(String name, CustomItem item){
        items.put(name, item);
    }

    public static Collection<CustomItem> getAllItems(){
        return items.values();
    }

    public static CustomItem getItemFromName(String key){
        return (CustomItem) items.get(key);
    }

    public static List<String> getAllKeys(){
        return new ArrayList<>(items.keySet());
    }
}
