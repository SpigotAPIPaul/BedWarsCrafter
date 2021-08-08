package net.craftergames.spigotpaul.bedwars.listeners;

import net.craftergames.spigotpaul.bedwars.config.Config;
import net.craftergames.spigotpaul.bedwars.gui.TeamGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickedListener implements Listener {

    @EventHandler
    public static void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if ((e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
            p.getItemInHand();
            if (p.getItemInHand().getType().equals(Material.valueOf(Config.team_item))) {
                TeamGUI.openTeamGui(p);
            }
        }
        }

}
