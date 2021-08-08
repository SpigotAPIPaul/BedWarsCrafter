package net.craftergames.spigotpaul.bedwars;


import net.craftergames.spigotpaul.bedwars.commands.SetPosCommand;
import net.craftergames.spigotpaul.bedwars.config.Config;
import net.craftergames.spigotpaul.bedwars.listeners.*;
import net.craftergames.spigotpaul.bedwars.methods.GameState.GameState;
import net.craftergames.spigotpaul.bedwars.methods.Scoreboard.ScoreBoard;
import net.craftergames.spigotpaul.bedwars.methods.plugin.Events;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.bukkit.Bukkit.getOnlinePlayers;
import static org.bukkit.Bukkit.getScheduler;

public final class BedWars extends JavaPlugin {

    public static String prefix = Config.config.getString("prefix");

    public static GameState gameState;

    public static GameState getGameState() {
        return gameState;
    }

    public static void setGameState(GameState gameState) {
        BedWars.gameState = gameState;
    }

    private void registerListeners() {
        Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BreakListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new GUIListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ClickedListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Events(this), this);
    }

    public void ScoreReloader() {
        getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run(){
                for (Player p : getOnlinePlayers()) {
                    ScoreBoard.updateScoreBoard(p);

                }

            }
        }, 20, 100);
    }

    private void createItemConfig() throws IOException, InvalidConfigurationException {
        if (!Config.config.isSet("item.team.int")) {
            Config.config.set("item.team.int", 4);
            Config.config.set("item.team.item", "BED");
            Config.config.set("item.team.name", "&8┌│ &3Teams");
            Config.config.save(Config.file);
            Config.config.load(Config.file);
        }
    }

    private void registerCommands() {
        getCommand("setpos").setExecutor(new SetPosCommand());
    }

    private void createPrefixConfig() throws IOException, InvalidConfigurationException {
        if (!Config.config.isSet("prefix")) {
            Config.config.set("prefix", "&8┌│ &9BedWars &8┃ &7");
            Config.config.save(Config.file);
            Config.config.load(Config.file);
        }
    }

    private void createConfig() {
        Config.writeCheck(new File("plugins/BedWars/config.yml"));
    }

    @Override
    public void onEnable() {

        registerListeners();
        registerCommands();
        createConfig();

        ScoreReloader();

        try {
            createPrefixConfig();
            createItemConfig();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        setGameState(GameState.LOBBY);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}