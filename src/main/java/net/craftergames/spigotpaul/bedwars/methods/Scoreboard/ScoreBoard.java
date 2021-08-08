package net.craftergames.spigotpaul.bedwars.methods.Scoreboard;

import net.craftergames.spigotpaul.bedwars.BedWars;
import net.craftergames.spigotpaul.bedwars.gui.TeamGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

public class ScoreBoard {

    public static void updateScoreBoard(Player player) {
        org.bukkit.scoreboard.ScoreboardManager sm = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard s = sm.getNewScoreboard();
        s.registerNewObjective("board", "dummy");
        Objective o = s.getObjective("board");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(BedWars.prefix.replaceAll("&", "§"));
        o.getScore("§7     §8§m-------------  §7").setScore(16);
        o.getScore("§7§8").setScore(15);
        o.getScore("§8┌│ §7Profil").setScore(14);
        o.getScore("§8» §e" + player.getName()).setScore(13);
        o.getScore("§9§3").setScore(12);
        o.getScore("§8┌│ §7Team").setScore(11);
        if (TeamGUI.team_blue.contains("§7" + player.getName())) {
            o.getScore("§8» §bBlue").setScore(10);
        } else if (TeamGUI.team_red.contains("§7" + player.getName())) {
            o.getScore("§8» §4Red").setScore(10);
        } else if (!TeamGUI.team_blue.contains("§7" + player.getName()) || !TeamGUI.team_red.contains("§7" + player.getName())) {
            o.getScore("§8» §cKeine").setScore(10);
        }
        o.getScore("§4§f§7").setScore(4);
        o.getScore("§8┌│ §7Spieler").setScore(3);
        o.getScore("§8» §e" + Bukkit.getServer().getOnlinePlayers().size()).setScore(2);
        o.getScore("§4§f").setScore(1);
        o.getScore("§7     §8§m-------------").setScore(0);
        player.setScoreboard(s);
    }

}
