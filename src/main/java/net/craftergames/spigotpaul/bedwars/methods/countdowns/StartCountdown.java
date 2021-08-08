package net.craftergames.spigotpaul.bedwars.methods.countdowns;

import net.craftergames.spigotpaul.bedwars.BedWars;
import net.craftergames.spigotpaul.bedwars.apis.Creator;
import net.craftergames.spigotpaul.bedwars.config.Config;
import net.craftergames.spigotpaul.bedwars.gui.TeamGUI;
import net.craftergames.spigotpaul.bedwars.methods.GameState.GameState;
import net.craftergames.spigotpaul.bedwars.methods.plugin.Events;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Objects;

public abstract class StartCountdown {

    static int countdown = 5;
    static int count;

    public static void CountDown(Player player){
        count = Bukkit.getScheduler().scheduleSyncRepeatingTask(Events.plugin, new Runnable() {

            @Override
            public void run() {
                if(countdown > 0){
                    Bukkit.broadcastMessage(BedWars.prefix.replaceAll("&", "§") + "§7In §8(§e" +countdown + "§8) §7Sekunden geht es los§8!");

                    for (Player broadcast : Bukkit.getOnlinePlayers()) {
                        broadcast.setLevel(countdown);
                    }

                    countdown--;
                }else{

                    Finish(player);

                    Bukkit.getScheduler().cancelTask(count);
                    countdown = 5;
                }

            }
        }, 0L, 20L);
    }

    public static void Finish(Player player) {
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.getInventory().clear();

            online.getInventory().setItem(0, Creator.itemcreator(Material.DIAMOND_SWORD, "", 1, (short) 0, null));
            online.getInventory().setItem(1, Creator.itemcreator(Material.SANDSTONE, "", 64, (short) 0, null));
            online.getInventory().setItem(2, Creator.itemcreator(Material.SANDSTONE, "", 64, (short) 0, null));
            online.getInventory().setItem(3, Creator.itemcreator(Material.SANDSTONE, "", 64, (short) 0, null));
            online.getInventory().setItem(4, Creator.itemcreator(Material.SANDSTONE, "", 64, (short) 0, null));

            BedWars.setGameState(GameState.INGAME);

            if (TeamGUI.team_red.contains("§7" + online.getPlayer().getName())) {
                online.teleport(Objects.requireNonNull(Config.config.getLocation("location.team.red")));
            } else if (TeamGUI.team_blue.contains("§7" + online.getPlayer().getName())) {
                online.teleport(Objects.requireNonNull(Config.config.getLocation("location.team.blue")));
            }
        }

    }


}
