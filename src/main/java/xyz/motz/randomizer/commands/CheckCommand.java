package xyz.motz.randomizer.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.motz.randomizer.main.Randomizer;

public class CheckCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Randomizer.getPlugin().enabled) {
            sender.sendMessage(ChatColor.AQUA + "[RANDOMIZER] " + ChatColor.GREEN
                    + "The Randomizer is currently activated!");
        } else {
            sender.sendMessage(ChatColor.AQUA + "[RANDOMIZER] " + ChatColor.GREEN
                    + "The Randomizer is currently deactivated!");
        }
        return true;
    }
}
