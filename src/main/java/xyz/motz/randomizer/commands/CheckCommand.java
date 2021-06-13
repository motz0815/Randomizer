package xyz.motz.randomizer.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandExecutor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import xyz.motz.randomizer.main.Randomizer;

public class CheckCommand extends CommandAPICommand implements CommandExecutor {

    public CheckCommand() {
        super("check");
        withPermission("randomizer.toggle");
        executes(this);
    }

    @Override
    public void run(CommandSender sender, Object[] args) {
        if (Randomizer.getPlugin().enabled) {
            sender.sendMessage(ChatColor.AQUA + "[RANDOMIZER] " + ChatColor.GREEN
                    + "The Randomizer is currently activated!");
        } else {
            sender.sendMessage(ChatColor.AQUA + "[RANDOMIZER] " + ChatColor.GREEN
                    + "The Randomizer is currently deactivated!");
        }
    }
}
