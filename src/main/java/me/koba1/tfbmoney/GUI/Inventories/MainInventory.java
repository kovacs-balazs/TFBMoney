package me.koba1.tfbmoney.GUI.Inventories;

import me.koba1.tfbmoney.GUI.GUIItems;
import me.koba1.tfbmoney.GUI.MainItems;
import me.koba1.tfbmoney.Vault.VaultAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainInventory {

    public Inventory inv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 5*9, "§8Bank");

        for(int i = 0; i < inv.getSize(); i++) {

            if(i < 3)
                inv.setItem(i, MainItems.darkgreen());

            else if(i == 3 || i == 5)
                inv.setItem(i, GUIItems.sunflower());

            else if(i < 9 && i > 5)
                inv.setItem(i, MainItems.lime());

            else if(i == 18 || i == 26)
                inv.setItem(i, MainItems.yellow());

            else if(i < 40 && i > 35)
                inv.setItem(i, MainItems.lime());

            else if(i < 45 && i > 40)
                inv.setItem(i, MainItems.darkgreen());
        }

        inv.setItem(9, MainItems.darkgreen());
        inv.setItem(17, MainItems.lime());

        inv.setItem(27, MainItems.lime());
        inv.setItem(35, MainItems.darkgreen());

        inv.setItem(4, GUIItems.anvil());
        inv.setItem(40, MainItems.Leave());

        inv.setItem(20, GUIItems.withdraw()); // emerald
        inv.setItem(24, GUIItems.deposit()); // redistoni
        inv.setItem(22, GUIItems.balance(new VaultAPI(p).getVaultBalance(), new VaultAPI(p).getHoldingBalance())); // nugget

        return inv;
    }
}
