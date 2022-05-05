package me.koba1.tfbmoney;

import me.koba1.tfbmoney.Commands.GUICommand;
import me.koba1.tfbmoney.GUI.Listeners.ChatEvent;
import me.koba1.tfbmoney.GUI.Listeners.MainInvEvent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public static HashMap<UUID, String> bankChat;

    @Override
    public void onEnable() {
        bankChat = new HashMap<>();
        // Plugin startup logic
        registerEvents();
        registerCommands();
        setupConfig();

        setupEconomy();
    }

    private void registerEvents() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new MainInvEvent(), this);
        manager.registerEvents(new ChatEvent(), this);
    }

    private void setupConfig() {
        getConfig().addDefault("prefix", "&8[§x§a§7§5§0§0§0T§x§b§1§5§f§0§8F§x§b§b§6§d§1§0B§x§c§6§7§c§1§9B§x§d§0§8§a§2§1a§x§d§a§9§9§2§9n§x§e§4§a§7§3§1k&8] > ");
        getConfig().addDefault("deposit", "%prefix% &aSikeresen betettél &2&n%deposit-balance%&a-t a bankba!");
        getConfig().addDefault("withdraw", "%prefix% &aSikeresen kivettél &2&n%withdraw-balance%&a-t a bankból!");
        getConfig().addDefault("failedmsg", "%prefix% &cHiba történt!");

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        saveConfig();
    }

    private void registerCommands() {
        getCommand("bank").setExecutor(new GUICommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEcon() {
        return econ;
    }

    public static final List<String> cancelMessages = Arrays.asList(
            "mégsem",
            "cancel",
            "megsem",
            "-1",
            "0"
    );

    public static final String cancelString = "'mégsem', 'cancel', '-1', '0'§7!"
            .replace("'", "§7'§6")
            .replace(",", "§7,");

}
