package net.craftergames.spigotpaul.bedwars.gui;

import net.craftergames.spigotpaul.bedwars.BedWars;
import net.craftergames.spigotpaul.bedwars.apis.Creator;
import net.craftergames.spigotpaul.bedwars.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TeamGUI implements Listener {

    public static
    ArrayList<String> team_blue = new ArrayList<>();

    public static
    ArrayList<String> team_red = new ArrayList<>();

    public static void openTeamGui(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, BedWars.prefix.replaceAll("&", "§") + Config.team_materialname.replaceAll("&", "§"));

        for (int i = 0; i < 27; i++) {
            ItemStack nop2 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
            ItemMeta nop2meta = nop2.getItemMeta();
            nop2meta.setDisplayName("§c-");
            nop2.setItemMeta(nop2meta);
            inv.setItem(i, nop2);
        }

        inv.setItem(12, Creator.itemcreator(Material.LIGHT_BLUE_DYE, "§bBlue", 1, (short)0, team_blue));
        inv.setItem(14, Creator.itemcreator(Material.RED_DYE, "§4Red", 1, (short)0, team_red));

        player.openInventory(inv);
    }

}
