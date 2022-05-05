package me.koba1.tfbmoney;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigAPI {

    private Main m = Main.getPlugin(Main.class);

    public String deposit() {
        return m.getConfig().getString("deposit");
    }

    public String withdraw() {
        return m.getConfig().getString("withdraw");
    }

    public String failed() {
        return m.getConfig().getString("failedmsg");
    }

    public String prefix() {
        return applyColor(m.getConfig().getString("prefix"));
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
