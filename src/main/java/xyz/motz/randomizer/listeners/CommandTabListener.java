package xyz.motz.randomizer.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.ArrayList;

public class CommandTabListener implements Listener {
    private final ArrayList<String> commands = new ArrayList<>();

    public CommandTabListener() {
        commands.add("shuffle");
        commands.add("check");
        commands.add("start");
        commands.add("stop");
    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent e) {
        if (e.getBuffer().startsWith("/randomizer")) {
            e.setCompletions(commands);
        }
    }
}
