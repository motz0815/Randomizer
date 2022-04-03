package xyz.motz.randomizer.main;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.motz.randomizer.commands.*;
import xyz.motz.randomizer.listeners.CommandTabListener;

import java.util.ArrayList;
import java.util.List;

public class Randomizer extends JavaPlugin implements Listener {

    public List<Material> remaining = new ArrayList<>();

    @Getter
    public static Randomizer plugin;

    public boolean enabled = this.getConfig().getBoolean("activated");

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new CommandTabListener(), this);

        getCommand("randomizer").setExecutor(new RandomizerCommand());

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();
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
