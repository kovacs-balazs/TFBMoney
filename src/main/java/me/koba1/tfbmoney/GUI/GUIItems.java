package me.koba1.tfbmoney.GUI;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUIItems implements Listener {

    public static ItemStack withdraw() {
        ItemStack is = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§7§l↓ " + applyColor("<#ff4343>P<#f73c3c>é<#ef3636>n<#e72f2f>z<#df2828>k<#d72222>i<#cf1b1b>v<#c71414>é<#bf0d0d>t<#b70707>e<#af0000>l"));
        im.setLore(Arrays.asList(
                "§5",
                "§7Kattins ide a pénz kivételéhez!"
        ));
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack deposit() {
        ItemStack is = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§7§L↑ " + applyColor("<#05df1b>P<#05d819>é<#04d017>n<#04c916>z<#03c114>f<#03ba12>e<#03b310>l<#02ab0e>t<#02a40c>ö<#019c0b>l<#019509>t<#008d07>é<#008605>s"));
        im.setLore(Arrays.asList(
                "§5",
                "§7Kattins ide a pénz feltöltéséhez!"
        ));
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack balance(double balance, int inventoryBalance) {
        ItemStack is = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§7[§6$§7] " + applyColor("<#ff8f0b>E<#f2880e>g<#e48111>y<#d77a14>e<#c97317>n<#bc6b1b>l<#ae641e>e<#a15d21>g<#935624>e<#864f27>d") + "§7: §6$" + (int) balance);
        im.setLore(Arrays.asList(
                "§5",
                applyColor("§7[§6$§7] <#da9307>N<#d58e08>á<#d0880a>l<#ca830b>a<#c57d0d>d <#c0780e>l<#bb720f>é<#b56d11>v<#b06812>ő <#ab6213>e<#a65d15>g<#a05716>y<#9b5218>e<#964c19>n<#91471a>l<#8b411c>e<#863c1d>g§7: §6$") + inventoryBalance
        ));
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack anvil() {
        ItemStack is = new ItemStack(Material.ANVIL);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(applyColor("<#a75000>B<#bb6d10>a<#d08a21>n<#e4a731>k"));
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack sunflower() {
        ItemStack is = new ItemStack(Material.SUNFLOWER);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§5");
        is.setItemMeta(im);
        return is;
    }


    private static final Pattern hexPattern = Pattern.compile("<#([A-Fa-f0-9]){6}>");
    //private static final Pattern hexPattern = Pattern.compile("#[a-fA-F0-9]{6}");
    public static String applyColor(String message) {
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
