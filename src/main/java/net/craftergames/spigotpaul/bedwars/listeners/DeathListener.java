package net.craftergames.spigotpaul.bedwars.listeners;

import net.craftergames.spigotpaul.bedwars.BedWars;
import net.craftergames.spigotpaul.bedwars.config.Config;
import net.craftergames.spigotpaul.bedwars.gui.TeamGUI;
import net.craftergames.spigotpaul.bedwars.methods.GameState.GameState;
import net.craftergames.spigotpaul.bedwars.methods.plugin.Events;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class DeathListener implements Listener {


    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        Player player = event.getEntity();
        event.setDeathMessage(null);
        if (TeamGUI.team_blue.contains("§7" + player.getName())) {
            if (BreakListener.blue) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(Events.plugin, new Runnable() {
                    public void run() {
                        event.getEntity().spigot().respawn();
                        player.teleport(Objects.requireNonNull(Config.config.getLocation("location.team.blue")));
                    }
                }, 4L);
                player.teleport(Objects.requireNonNull(Config.config.getLocation("location.team.blue")));
            } else {
                player.sendMessage(BedWars.prefix.replaceAll("&", "§") + "§cDu bist ausgeschieden§8!");
                Bukkit.getScheduler().scheduleSyncDelayedTask(Events.plugin, new Runnable() {
                    public void run() {
                        event.getEntity().spigot().respawn();
                    }
                }, 4L);
                BedWars.setGameState(GameState.END);
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(BedWars.prefix.replaceAll("&", "§") + "§aTeam Rot hat gewonnen§8!");
                Bukkit.broadcastMessage("");
                player.setGameMode(GameMode.SPECTATOR);

                Player broadcast = (Player) Bukkit.getOnlinePlayers();

                broadcast.teleport(Objects.requireNonNull(Config.config.getLocation("lobby")));
            }
        } else if (TeamGUI.team_red.contains("§7" + player.getName())) {
            if (BreakListener.red) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(Events.plugin, new Runnable() {
                    public void run() {
                        event.getEntity().spigot().respawn();
                        player.teleport(Objects.requireNonNull(Config.config.getLocation("location.team.red")));
                        player.teleport(Objects.requireNonNull(Config.config.getLocation("location.team.red")));
                    }
                }, 4L);
            } else {
                player.sendMessage(BedWars.prefix.replaceAll("&", "§") + "§cDu bist ausgeschieden§8!");
                Bukkit.getScheduler().scheduleSyncDelayedTask(Events.plugin, new Runnable() {
                    public void run() {
                        event.getEntity().spigot().respawn();
                    }
                }, 4L);
                BedWars.setGameState(GameState.END);
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(BedWars.prefix.replaceAll("&", "§") + "§aTeam Blau hat gewonnen§8!");
                Bukkit.broadcastMessage("");

                Player broadcast = (Player) Bukkit.getOnlinePlayers();

                broadcast.teleport(Objects.requireNonNull(Config.config.getLocation("lobby")));

            }
        }

    }


}
