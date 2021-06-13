package xyz.motz.randomizer.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandExecutor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class RandomizerCommand extends CommandAPICommand implements CommandExecutor {

    public RandomizerCommand() {
        super("randomizer");
        withSubcommand(new ShuffleCommand());
        withSubcommand(new StartCommand());
        withSubcommand(new StopCommand());
        withSubcommand(new CheckCommand());

        executes(this);
    }

    @Override
    public void run(CommandSender sender, Object[] args) {
        sender.sendMessage(ChatColor.AQUA + "[RANDOMIZER] " + ChatColor.GREEN + "Usage: " + ChatColor.GOLD
                + "/randomizer shuffle/check/start/stop");
    }
}
