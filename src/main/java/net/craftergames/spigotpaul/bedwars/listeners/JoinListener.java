package net.craftergames.spigotpaul.bedwars.listeners;

import net.craftergames.spigotpaul.bedwars.BedWars;
import net.craftergames.spigotpaul.bedwars.apis.Creator;
import net.craftergames.spigotpaul.bedwars.config.Config;
import net.craftergames.spigotpaul.bedwars.gui.TeamGUI;
import net.craftergames.spigotpaul.bedwars.methods.GameState.GameState;
import net.craftergames.spigotpaul.bedwars.methods.Scoreboard.ScoreBoard;
import net.craftergames.spigotpaul.bedwars.methods.countdowns.StartCountdown;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class JoinListener implements Listener {

    private void giveItems(Player player) {
        player.getInventory().setItem(Config.team_inventar, Creator.itemcreator(Material.valueOf(Config.team_item), Config.team_materialname.replaceAll("&", "§"), 1, (short) 0, null));
    }

    @EventHandler
    public void fakeJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        event.setJoinMessage(null);
        ScoreBoard.updateScoreBoard(player);

        player.getInventory().clear();

        giveItems(player);

        player.teleport(Objects.requireNonNull(Config.config.getLocation("lobby")));

        if (BedWars.getGameState().equals(GameState.LOBBY)) {
            if (Bukkit.getOnlinePlayers().size() < 2) {
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(BedWars.prefix.replaceAll("&", "§") + "§cWir brauchen noch einen Spieler§8!");
                Bukkit.broadcastMessage(BedWars.prefix.replaceAll("&", "§") + "§7Spieler " + player.getName() + " ist beigetretten§8!");
                Bukkit.broadcastMessage("");
            } else if (Bukkit.getOnlinePlayers().size() > 1) {
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(BedWars.prefix.replaceAll("&", "§") + "§aDas Spiel geht jetzt los§8!");
                Bukkit.broadcastMessage(BedWars.prefix.replaceAll("&", "§") + "§7Spieler " + player.getName() + " ist beigetretten§8!");
                Bukkit.broadcastMessage("");
                StartCountdown.CountDown(player);
            } else if (Bukkit.getOnlinePlayers().size() < 2) {
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(BedWars.prefix.replaceAll("&", "§") + "§aDas Spiel wurde beendet§8!");
                Bukkit.broadcastMessage("");
            }
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        event.setQuitMessage(null);

        Player player = event.getPlayer();

        TeamGUI.team_blue.remove("§7" + player.getName());
        TeamGUI.team_red.remove("§7" + player.getName());


    }

    @EventHandler
    public void onJoin(PlayerLoginEvent event) {

        if (BedWars.getGameState().equals(GameState.INGAME)) {
            event.getPlayer().kickPlayer("\n" + BedWars.prefix.replaceAll("&", "§") + "§cDas Spiel ist bereits gestartet§8!\n");
        }

    }
}
