package xyz.motz.randomizer.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.motz.randomizer.main.Randomizer;

public class StopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!Randomizer.getPlugin().enabled) {
            sender.sendMessage(ChatColor.AQUA + "[RANDOMIZER] " + ChatColor.GREEN
                    + "The Randomizer is already deactivated!");
        } else {
            Randomizer.getPlugin().getConfig().set("activated", false);
            Randomizer.getPlugin().saveConfig();
            Randomizer.getPlugin().enabled = false;
            sender.sendMessage(ChatColor.AQUA + "[RANDOMIZER] " + ChatColor.GREEN
                    + "Successfully deactivated the Randomizer!");
        }
        return true;
    }
}
