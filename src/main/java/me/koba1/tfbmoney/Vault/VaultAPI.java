package me.koba1.tfbmoney.Vault;

import io.th0rgal.oraxen.OraxenPlugin;
import io.th0rgal.oraxen.items.OraxenItems;
import me.koba1.tfbmoney.Main;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.framework.qual.FromStubFile;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VaultAPI {
    private Main m = Main.getPlugin(Main.class);
    Player p;

    public VaultAPI(Player p) {
        this.p = p;
    }

    public double getVaultBalance() {
        return Main.getEcon().getBalance(p.getName());
    }

    public int getHoldingBalance() {
        int amount = 0;
        for(ItemStack is : p.getInventory().getStorageContents()) {
            if(is != null) {
                if(is.getItemMeta().getDisplayName().equalsIgnoreCase("§x§F§A§7§C§B§BR§x§F§9§7§3§A§Bu§x§F§7§6§A§9§Ab§x§F§6§6§1§8§Ai§x§F§4§5§8§7§9n§x§F§3§4§F§6§8t"))
                    amount += is.getAmount();
            }
        }
        return amount;
    }

    public boolean deposit(double amount) {
        if(getHoldingBalance() >= amount) {
            EconomyResponse r = Main.getEcon().depositPlayer(p.getName(), amount);
            int itemsToRemove = (int) amount;
            for(ItemStack is : p.getInventory().getStorageContents()) {
                if(is != null) {
                    if(is.getItemMeta().getDisplayName().equalsIgnoreCase("§x§F§A§7§C§B§BR§x§F§9§7§3§A§Bu§x§F§7§6§A§9§Ab§x§F§6§6§1§8§Ai§x§F§4§5§8§7§9n§x§F§3§4§F§6§8t")) {
                        int preAmount = is.getAmount();
                        int newAmount = Math.max(0, preAmount - itemsToRemove);
                        itemsToRemove = Math.max(0, itemsToRemove - preAmount);
                        is.setAmount(newAmount);
                        if(itemsToRemove == 0) {
                            break;
                        }
                    }
                }
            }
            return r.transactionSuccess();
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if(getVaultBalance() >= amount) {
            EconomyResponse r = Main.getEcon().withdrawPlayer(p.getName(), amount);
            ItemStack is = m.getConfig().getItemStack("RubyStack");
            is.setAmount((int) amount);
            p.getInventory().addItem(is);
            return r.transactionSuccess();
        }
        return false;
    }

    public int freeSlotWithRubys() {
        int free = 0;
        for(ItemStack is : p.getInventory().getStorageContents()) {
            if(is != null) {
                if(is.getItemMeta().getDisplayName().equalsIgnoreCase("§x§F§A§7§C§B§BR§x§F§9§7§3§A§Bu§x§F§7§6§A§9§Ab§x§F§6§6§1§8§Ai§x§F§4§5§8§7§9n§x§F§3§4§F§6§8t")) {
                    if(is.getAmount() < 64) {
                        free += (64 - is.getAmount());
                    }
                }
            } else if(is == null) {
                free += 64;
            }
        }
        return free;
    }
}
