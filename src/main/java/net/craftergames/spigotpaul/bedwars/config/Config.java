package net.craftergames.spigotpaul.bedwars.config;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    public static File file = new File("plugins/BedWars/", "config.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    public static int team_inventar = config.getInt("item.team.int");
    public static String team_item = config.getString("item.team.item");
    public static String team_materialname = config.getString("item.team.name");

    public static boolean writeCheck(File file)
    {
        boolean f = true;
        if(!file.exists()) try
        {
            if(!file.getParentFile().exists() && !file.getParentFile().mkdirs())
            {
                System.err.println(
                        "writeCheck error: Cannot create parent file " + file.getParentFile().getAbsolutePath());
                f = false;
            }
            if(!file.createNewFile())
            {
                System.err.println("writeCheck error: Cannot create file " + file.getAbsolutePath());
                f = false;
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            f = false;
        }
        if(!file.canWrite())
            f = false;
        return f;
    }

}
