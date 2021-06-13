package xyz.motz.randomizer.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandExecutor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import xyz.motz.randomizer.main.Randomizer;

import java.util.Random;

public class ShuffleCommand extends CommandAPICommand implements CommandExecutor {

    public ShuffleCommand() {
        super("shuffle");
        withPermission("randomizer.shuffle");
        executes(this);
    }

    @Override
    public void run(CommandSender sender, Object[] args) {
        Randomizer.getPlugin().remaining.clear();

        for (Material mat : Material.values()) {
            if (mat.isItem()) {
                Randomizer.getPlugin().remaining.add(mat);
            }
        }

        for (Material mat : Material.values()) {
            if (!Randomizer.getPlugin().remaining.isEmpty()) {
                if (mat.isBlock()) {
                    Random r = new Random();
                    int rand;
                    if (Randomizer.getPlugin().remaining.size() != 1) {
                        rand = r.nextInt(Randomizer.getPlugin().remaining.size() - 1);
                    } else {
                        rand = 0;
                    }
                    Randomizer.getPlugin().getConfig().set("partners." + mat, Randomizer.getPlugin().remaining.get(rand).toString());
                    Randomizer.getPlugin().remaining.remove(rand);
                }

            }

        }
        Randomizer.getPlugin().saveConfig();

        sender.sendMessage(ChatColor.AQUA + "[RANDOMIZER] " + ChatColor.GREEN
                + "The Random Pairs were successfully regenerated!");
    }
}
