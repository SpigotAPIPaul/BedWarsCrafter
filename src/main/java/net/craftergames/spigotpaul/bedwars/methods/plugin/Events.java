package net.craftergames.spigotpaul.bedwars.methods.plugin;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class Events implements Listener {

    public static Plugin plugin;

    public Events(Plugin plugin){
        Events.plugin = plugin;
    }

}
