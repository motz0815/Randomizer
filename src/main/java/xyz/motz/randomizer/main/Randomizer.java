package xyz.motz.randomizer.main;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIConfig;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.motz.randomizer.commands.RandomizerCommand;

import java.util.ArrayList;
import java.util.List;

public class Randomizer extends JavaPlugin implements Listener {

    public List<Material> remaining = new ArrayList<>();

    @Getter
    public static Randomizer plugin;

    public boolean enabled = this.getConfig().getBoolean("activated");

    @Override
    public void onLoad() {

        CommandAPIConfig commandAPIConfig = new CommandAPIConfig();
        commandAPIConfig.setVerboseOutput(false);

        CommandAPI.onLoad(commandAPIConfig);
    }

    @Override
    public void onEnable() {
        plugin = this;

        CommandAPI.onEnable(this);

        Bukkit.getPluginManager().registerEvents(this, this);

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        new RandomizerCommand().register();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (this.enabled) {
            e.setDropItems(false);
            if (e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                        new ItemStack(getPartner(e.getBlock().getType())));
            }
        }
    }

    public Material getPartner(Material mat) {
        Material randpart;
        try {
            randpart = Material.valueOf(this.getConfig().getString("partners." + mat.toString()));
        } catch (Exception e) {
            randpart = mat;
        }
        return randpart;
    }

}
