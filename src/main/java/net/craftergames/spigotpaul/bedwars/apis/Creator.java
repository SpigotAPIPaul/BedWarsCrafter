package net.craftergames.spigotpaul.bedwars.apis;


import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;


public class Creator implements Listener {
      public static ItemStack itemcreator(Material material, String name, int anzahl, short subid, List<String> lore) {
            ItemStack item = new ItemStack(material, anzahl, subid);
            ItemMeta itemmeta = item.getItemMeta();
            itemmeta.setDisplayName(name);
            itemmeta.setLore(lore);
            item.setItemMeta(itemmeta);
            return item;
          }


      public static ItemStack enchantcreator(Material material, String name, int anzahl, short subid, ArrayList<String> lore, Enchantment enchant, int zahl) {
            ItemStack item = new ItemStack(material, anzahl, subid);
            ItemMeta itemmeta = item.getItemMeta();
            itemmeta.setDisplayName(name);
            itemmeta.setLore(lore);
            itemmeta.addEnchant(enchant, zahl, true);
            item.setItemMeta(itemmeta);
            return item;
          }

      public static ItemStack Headcreator(String name, String owner, int anzahl, ArrayList<String> lore) {
            ItemStack head = new ItemStack(Material.SKELETON_SKULL, anzahl, (short)3);
            SkullMeta headmeta = (SkullMeta)head.getItemMeta();
            headmeta.setDisplayName(name);
            headmeta.setOwner(owner);
            headmeta.setLore(lore);
            head.setItemMeta((ItemMeta)headmeta);
            return head;
          }

      public static ItemStack bootscreator(String name, Color color) {
            ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
            LeatherArmorMeta imeta = (LeatherArmorMeta)item.getItemMeta();
            imeta.setDisplayName(name);
            imeta.setColor(color);
            item.setItemMeta((ItemMeta)imeta);
            return item;
          }




      public static ItemStack Headcreator(String setOwner, int i, short anzahl, Object lore) {
       /* 61 */     return null;
       /*    */   }

      public static ItemStack Headcreator(Material skullItem, int i, short s) {
       /* 66 */     return null;
       /*    */   }

      public static void itemcreator(Material material) {
      }
}