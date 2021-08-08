package net.craftergames.spigotpaul.bedwars.listeners;

import net.craftergames.spigotpaul.bedwars.BedWars;
import net.craftergames.spigotpaul.bedwars.config.Config;
import net.craftergames.spigotpaul.bedwars.gui.TeamGUI;
import net.craftergames.spigotpaul.bedwars.methods.Scoreboard.ScoreBoard;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.Objects;

public class GUIListener implements Listener {


    @EventHandler
    public void NoneClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (Objects.requireNonNull(Objects.requireNonNull(e.getCurrentItem()).getItemMeta()).getDisplayName().equalsIgnoreCase("§c-")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void BlueClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (Objects.requireNonNull(Objects.requireNonNull(e.getCurrentItem()).getItemMeta()).getDisplayName().equalsIgnoreCase("§bBlue")) {
            if (TeamGUI.team_blue.size() > 0) {
                p.closeInventory();
                p.sendMessage(BedWars.prefix.replaceAll("&", "§") + "§cDas Team ist schon voll§8!");
            } else {
                if (TeamGUI.team_blue.contains("§7" + p.getName())) {
                    p.sendMessage(BedWars.prefix.replaceAll("&", "§") + "§cDu bist schon im Team§8!");
                    p.closeInventory();
                } else {
                    TeamGUI.team_blue.add("§7" + p.getName());
                    p.sendMessage(BedWars.prefix.replaceAll("&", "§") + "§aDu bist jetzt in Team §bBlau§8!");
                    TeamGUI.team_red.remove("§7" + p.getName());
                    p.closeInventory();
                    ScoreBoard.updateScoreBoard(p);
                }
            }
        }
    }

    @EventHandler
    public void RedClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (Objects.requireNonNull(Objects.requireNonNull(e.getCurrentItem()).getItemMeta()).getDisplayName().equalsIgnoreCase("§4Red")) {
            if (TeamGUI.team_red.size() > 0) {
                p.closeInventory();
                p.sendMessage(BedWars.prefix.replaceAll("&", "§") + "§cDas Team ist schon voll§8!");
            } else {
                if (TeamGUI.team_red.contains("§7" + p.getName())) {
                    p.sendMessage(BedWars.prefix.replaceAll("&", "§") + "§cDu bist schon im Team§8!");
                    p.closeInventory();
                } else {
                    TeamGUI.team_red.add("§7" + p.getName());
                    p.sendMessage(BedWars.prefix.replaceAll("&", "§") + "§aDu bist jetzt in Team §4Rot§8!");
                    TeamGUI.team_blue.remove("§7" + p.getName());
                    p.closeInventory();
                    ScoreBoard.updateScoreBoard(p);
                }
            }
        }
    }


}
