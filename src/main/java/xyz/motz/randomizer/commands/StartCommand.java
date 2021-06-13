package xyz.motz.randomizer.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandExecutor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import xyz.motz.randomizer.main.Randomizer;

public class StartCommand extends CommandAPICommand implements CommandExecutor {

    public StartCommand() {
        super("start");
        withPermission("randomizer.toggle");
        executes(this);
    }

    @Override
    public void run(CommandSender sender, Object[] args) {
        if (Randomizer.getPlugin().enabled) {
            sender.sendMessage(ChatColor.AQUA + "[RANDOMIZER] " + ChatColor.GREEN
                    + "The Randomizer is already activated!");
        } else {
            Randomizer.getPlugin().getConfig().set("activated", true);
            Randomizer.getPlugin().saveConfig();
            Randomizer.getPlugin().enabled = true;
            sender.sendMessage(ChatColor.AQUA + "[RANDOMIZER] " + ChatColor.GREEN
                    + "Successfully activated the Randomizer!");
        }
    }
}
