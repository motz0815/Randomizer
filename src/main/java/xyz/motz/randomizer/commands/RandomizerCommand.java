package xyz.motz.randomizer.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RandomizerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("shuffle")) {
                if (sender.hasPermission("randomizer.shuffle")) {
                    return new ShuffleCommand().onCommand(sender, command, label, args);
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                    return true;
                }
            } else if (args[0].equalsIgnoreCase("start")) {
                if (sender.hasPermission("randomizer.toggle")) {
                    return new StartCommand().onCommand(sender, command, label, args);
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                    return true;
                }
            } else if (args[0].equalsIgnoreCase("stop")) {
                if (sender.hasPermission("randomizer.toggle")) {
                    return new StopCommand().onCommand(sender, command, label, args);
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                    return true;
                }
            } else if (args[0].equalsIgnoreCase("check")) {
                if (sender.hasPermission("randomizer.toggle")) {
                    return new CheckCommand().onCommand(sender, command, label, args);
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                    return true;
                }
            }
        }

        sender.sendMessage(ChatColor.AQUA + "[RANDOMIZER] " + ChatColor.GREEN + "Usage: " + ChatColor.GOLD
                + "/randomizer shuffle/check/start/stop");
        return true;
    }
}
