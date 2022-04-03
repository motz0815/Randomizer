package xyz.motz.randomizer.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.motz.randomizer.main.Randomizer;

import java.util.Random;

public class ShuffleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
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
        return true;
    }
}
