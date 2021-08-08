package net.craftergames.spigotpaul.bedwars.listeners;

import net.craftergames.spigotpaul.bedwars.BedWars;
import net.craftergames.spigotpaul.bedwars.gui.TeamGUI;
import net.craftergames.spigotpaul.bedwars.methods.GameState.GameState;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;

public class BreakListener implements Listener {

    public static boolean blue = true;
    public static boolean red = true;

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();

        if (BedWars.getGameState().equals(GameState.LOBBY)) {
            event.setCancelled(true);
        } else if (BedWars.getGameState().equals(GameState.INGAME)) {
            if(event.getBlock().getType() == Material.BLUE_BED) {
                if (!TeamGUI.team_blue.contains("§7" + player.getName())) {

                    Bukkit.broadcastMessage("§7");
                    Bukkit.broadcastMessage(BedWars.prefix.replaceAll("&", "§") + "§cTeam Blau hat kein Bett mehr§8!");
                    Bukkit.broadcastMessage("§7");

                    blue=false;

                    event.getBlock().setType(Material.AIR);

                    for (Player allplayer : Bukkit.getOnlinePlayers()) {
                        allplayer.playSound(allplayer.getLocation(), Sound.ENTITY_WITHER_DEATH, 100, 100);
                    }
                } else {
                    player.sendMessage("");
                    player.sendMessage(BedWars.prefix.replaceAll("&", "§") + "§cDu kannst dein Bett nicht abbauen§8!");
                    player.sendMessage("");
                    event.setCancelled(true);
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 100, 100);
                }

            }

            if(event.getBlock().getType() == Material.RED_BED) {
                if (!TeamGUI.team_red.contains("§7" + player.getName())) {

                    Bukkit.broadcastMessage("§7");
                    Bukkit.broadcastMessage(BedWars.prefix.replaceAll("&", "§") + "§cTeam Rot hat kein Bett mehr§8!");
                    Bukkit.broadcastMessage("§7");

                    red=false;

                    event.getBlock().setType(Material.AIR);

                    for (Player allplayer : Bukkit.getOnlinePlayers()) {
                        allplayer.playSound(allplayer.getLocation(), Sound.ENTITY_WITHER_DEATH, 100, 100);
                    }

                } else {
                    player.sendMessage("");
                    player.sendMessage(BedWars.prefix.replaceAll("&", "§") + "§cDu kannst dein Bett nicht abbauen§8!");
                    player.sendMessage("");
                    event.setCancelled(true);
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 100, 100);
                }
            }

        }

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (BedWars.getGameState().equals(GameState.LOBBY)) {
            event.setCancelled(true);
        }
    }


}
