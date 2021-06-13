package xyz.motz.randomizer.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandExecutor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import xyz.motz.randomizer.main.Randomizer;

public class StopCommand extends CommandAPICommand implements CommandExecutor {

    public StopCommand() {
        super("stop");
        withPermission("randomizer.toggle");
        executes(this);
    }

    @Override
    public void run(CommandSender sender, Object[] args) {
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
    }
}
