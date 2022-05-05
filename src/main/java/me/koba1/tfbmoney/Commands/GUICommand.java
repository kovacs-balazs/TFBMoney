package me.koba1.tfbmoney.Commands;

import me.koba1.tfbmoney.GUI.Inventories.MainInventory;
import me.koba1.tfbmoney.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GUICommand implements CommandExecutor {
    private Main m = Main.getPlugin(Main.class);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(cmd.getName().equalsIgnoreCase("bank")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(args.length == 0) {
                    p.openInventory(new MainInventory().inv(p));
                }
            }
        }
        return false;
    }
}
