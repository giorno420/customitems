package xyz.minefalls.minefallsitems.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.minefalls.minefallsitems.Main;

public class CustomItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp() || !(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "You can't use this command :/");
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0){
            player.sendRawMessage(ChatColor.RED + "Wrong command usage!");
            return true;
        }

        if (args[0].equals("give")){
            int amount = 1;
            if (args.length > 2){
                amount = Integer.parseInt(args[2]);
            }

            ItemStack item = Main.getItemFromName(args[1]).createItem(1);

            if (item != null){
                player.getInventory().addItem(item);
                player.sendRawMessage("Gave you " + item.getItemMeta().getDisplayName());
            }

            else {
                player.sendRawMessage(ChatColor.RED + "That item doesn't exist!");
            }
        }
        if (args[0].equals("list")){
            player.sendRawMessage(ChatColor.GOLD + "A list of all available items");
            Main.getAllKeys().forEach(player::sendRawMessage);
        }
        return true;
    }
}
