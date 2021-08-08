package net.craftergames.spigotpaul.bedwars.commands;

import net.craftergames.spigotpaul.bedwars.BedWars;
import net.craftergames.spigotpaul.bedwars.config.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.IOException;

public class SetPosCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (player.hasPermission("setpos.perms")) {
            if (args.length == 0) {
                player.sendMessage("");
                player.sendMessage(BedWars.prefix.replaceAll("&", "§") + "/setpos §8(§4Red§8/§bBlue§8)");
                player.sendMessage("");
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("lobby")) {
                    Config.config.set("lobby", player.getLocation());
                    player.sendMessage("");
                    player.sendMessage(BedWars.prefix.replaceAll("&", "§") + "§7Die Location für die Lobby §7wurde gesetzt§8!");
                    player.sendMessage("");

                    try {
                        Config.config.save(Config.file);
                        Config.config.load(Config.file);
                    } catch (IOException | InvalidConfigurationException e) {
                        e.printStackTrace();
                    }

                }
                if (args[0].equalsIgnoreCase("red")) {
                    Config.config.set("location.team.red", player.getLocation());
                    player.sendMessage("");
                    player.sendMessage(BedWars.prefix.replaceAll("&", "§") + "§7Die Location für §4Rot §7wurde gesetzt§8!");
                    player.sendMessage("");

                    try {
                        Config.config.save(Config.file);
                        Config.config.load(Config.file);
                    } catch (IOException | InvalidConfigurationException e) {
                        e.printStackTrace();
                    }

                }
                if (args[0].equalsIgnoreCase("blue")) {
                    Config.config.set("location.team.blue", player.getLocation());
                    player.sendMessage("");
                    player.sendMessage(BedWars.prefix.replaceAll("&", "§") + "§7Die Location für §bBlue §7wurde gesetzt§8!");
                    player.sendMessage("");

                    try {
                        Config.config.save(Config.file);
                        Config.config.load(Config.file);
                    } catch (IOException | InvalidConfigurationException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return false;
    }
}
