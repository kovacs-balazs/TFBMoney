package me.koba1.tfbmoney.GUI.Listeners;

import me.koba1.tfbmoney.ConfigAPI;
import me.koba1.tfbmoney.Main;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MainInvEvent implements Listener {

    @EventHandler
    public void onClickInv(InventoryClickEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("§8Bank")) {
            e.setCancelled(true);
            if(e.getCurrentItem() != null) {
                if(e.getCurrentItem().getType() != Material.AIR) {
                    if(e.getCurrentItem().getType() == Material.EMERALD_BLOCK) {
                        e.getWhoClicked().closeInventory();

                        e.getWhoClicked().sendMessage(new ConfigAPI().prefix() + "§7Megszakító szavak: " + Main.cancelString);
                        e.getWhoClicked().sendMessage(new ConfigAPI().prefix() + "§aÍrd be az összeget!");

                        Main.bankChat.put(e.getWhoClicked().getUniqueId(), "deposit");

                    } else if(e.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
                        e.getWhoClicked().closeInventory();

                        e.getWhoClicked().sendMessage(new ConfigAPI().prefix() + "§7Megszakító szavak: " + Main.cancelString);
                        e.getWhoClicked().sendMessage(new ConfigAPI().prefix() + "§aÍrd be az összeget!");

                        Main.bankChat.put(e.getWhoClicked().getUniqueId(), "withdraw");
                    }
                    //
                    else if(e.getCurrentItem().getType() == Material.DARK_OAK_DOOR) {
                        if(e.getCurrentItem().hasItemMeta()) {
                            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cKilépés")) {
                                e.getWhoClicked().closeInventory();
                            }
                        }
                    }
                }
            }
        }
    }
}
