package me.koba1.tfbmoney.GUI.Listeners;

import me.koba1.tfbmoney.ConfigAPI;
import me.koba1.tfbmoney.Main;
import me.koba1.tfbmoney.Vault.VaultAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatEvent implements Listener {
    //public static ChatEvent.CancelMessages CancelMessages;


    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (Main.bankChat.containsKey(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            Player p = e.getPlayer();
            String text = e.getMessage();

            e.getPlayer().sendMessage(new ConfigAPI().prefix() + "§7Megszakító szavak: " + Main.cancelString);

            if (Main.cancelMessages.contains(e.getMessage().toLowerCase())) {
                Main.bankChat.remove(e.getPlayer().getUniqueId());
                e.getPlayer().sendMessage(new ConfigAPI().prefix() + "§cSikeres megszakítás!");
                return;
            }
            //
            else if (Main.bankChat.get(e.getPlayer().getUniqueId()).equalsIgnoreCase("withdraw")) {
                if (text.matches("^[0-9]+$")) {
                    try {
                        if (new VaultAPI(p).getVaultBalance() >= Integer.parseInt(text)) {
                            if (new VaultAPI(p).freeSlotWithRubys() < Integer.parseInt(text)) {
                                p.sendMessage(new ConfigAPI().prefix() + "§cNincs elég üres hely nálad!");
                                return;
                            }
                            if (new VaultAPI(p).withdraw(Integer.parseInt(text))) {
                                p.sendMessage(new ConfigAPI().prefix() +
                                        applyColor("<#55ff55>S<#52fc52>i<#4ff94f>k<#4cf64c>e<#49f349>r<#46f046>e<#43ed43>s<#40ea40>e<#3ee83e>n " +
                                                "<#3be53b>k<#38e238>i<#35df35>v<#32dc32>e<#2fd92f>t<#2cd62c>t<#29d329>é<#26d026>l <#23cd23>a " +
                                                "<#20ca20>b<#1dc71d>a<#1ac41a>n<#17c117>k<#15bf15>b<#12bc12>ó<#0fb90f>l " +
                                                "§a$" + text + "§2-t!"));
                                Main.bankChat.remove(e.getPlayer().getUniqueId());
                            } else {
                                p.sendMessage(new ConfigAPI().prefix() + "§cHiba történt!");
                                Main.bankChat.remove(e.getPlayer().getUniqueId());
                            }
                        } else
                            p.sendMessage(new ConfigAPI().prefix() + "§cNincsen a bankodban elég pénz a kivételhez!");
                    } catch (NumberFormatException ex) {
                        p.sendMessage(new ConfigAPI().prefix() + "§cKérlek kisebb számot adj meg!");
                    }
                } else
                    p.sendMessage(new ConfigAPI().prefix() + "§cKérlek számot adj meg!");

            }
            //
            else if (Main.bankChat.get(e.getPlayer().getUniqueId()).equalsIgnoreCase("deposit")) {
                if (text.matches("^[0-9]+$")) {
                    try {
                        if (new VaultAPI(p).getHoldingBalance() >= Integer.parseInt(text)) {
                            if (new VaultAPI(p).deposit(Integer.parseInt(text))) {
                                p.sendMessage(new ConfigAPI().prefix() +
                                        applyColor("<#55ff55>S<#52fc52>i<#4ff94f>k<#4cf64c>e<#49f349>r<#46f046>e<#43ed43>s<#40ea40>e<#3de73d>n " +
                                                "<#3ae43a>b<#37e137>e<#34de34>t<#31db31>e<#2ed82e>t<#2bd52b>t<#27d127>é<#24ce24>l <#21cb21>a " +
                                                "<#1ec81e>b<#1bc51b>a<#18c218>n<#15bf15>k<#12bc12>b<#0fb90f>a " +
                                                "§a$" + text+ "§2-t!"));
                                Main.bankChat.remove(e.getPlayer().getUniqueId());
                            } else {
                                p.sendMessage(new ConfigAPI().prefix() + "§cHiba történt!");
                                Main.bankChat.remove(e.getPlayer().getUniqueId());
                            }
                        } else
                            p.sendMessage(new ConfigAPI().prefix() + "§cNincsen nálad elég pénz!");
                    } catch (NumberFormatException ex) {
                        p.sendMessage(new ConfigAPI().prefix() + "§cKérlek kisebb számot adj meg!");
                    }
                } else
                    p.sendMessage(new ConfigAPI().prefix() + "§cKérlek számot adj meg!");
            }
        }
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