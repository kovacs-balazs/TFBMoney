package me.koba1.tfbmoney.GUI;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainItems {
    public static ItemStack Glass() {
        ItemStack is = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§5");
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack Back() {
        ItemStack is = new ItemStack(Material.RED_DYE);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§cVissza");
        is.setItemMeta(im);
        return is;

    }

    public static ItemStack Leave() {
        ItemStack is = new ItemStack(Material.DARK_OAK_DOOR);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§cKilépés");
        im.addEnchant(Enchantment.DURABILITY, 2, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack darkgreen() {
        ItemStack is = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§5");
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack lime() {
        ItemStack is = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§5");
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack yellow() {
        ItemStack is = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§5");
        is.setItemMeta(im);
        return is;
    }


    private final Pattern hexPattern = Pattern.compile("<#([A-Fa-f0-9]){6}>");
    public String applyColor(String message){
        Matcher matcher = hexPattern.matcher(message);
        while (matcher.find()) {
            final ChatColor hexColor = net.md_5.bungee.api.ChatColor.of(matcher.group().substring(1, matcher.group().length() - 1));
            final String before = message.substring(0, matcher.start());
            final String after = message.substring(matcher.end());
            message = before + hexColor + after;
            matcher = hexPattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
