package fr.ward.dynamite.commands;

import fr.ward.dynamite.Dynamite;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DynamiteCommand implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            final Player player = ((Player) commandSender).getPlayer();

            if(args.length == 1) {
                if(args[0].equals("credit") || args[0].equals("dev") || args[0].equals("plugin")) {
                    player.sendMessage(Dynamite.getInstance().getPrefix() + ChatColor.GRAY + " Created by " + ChatColor.AQUA +  "Ward" + ChatColor.GRAY + " with " + ChatColor.DARK_RED + "❤" + ChatColor.DARK_GRAY + " (Plugin Free)");
                    player.sendMessage(ChatColor.BLUE + "[Discord] " + ChatColor.GRAY + "https://discord.gg/cJF48s3SBJ" + ChatColor.GRAY + " (" + ChatColor.YELLOW + "Click" + ChatColor.GRAY + ")");
                    return true;
                }
            }

            if(player.isOp() || player.hasPermission("dynamite.give")) {
                if (args.length == 0) {
                    player.getInventory().addItem(Dynamite.getInstance().getDynamite());
                    player.updateInventory();
                    return true;
                }

                if(args.length == 2) {
                    if(args[0].contains("give")) {
                        final Player receiver = Bukkit.getPlayer(args[1]);

                        if(receiver.isOnline()) {
                            receiver.getInventory().addItem(Dynamite.getInstance().getDynamite());
                            receiver.updateInventory();
                        } else {
                            player.sendMessage(Dynamite.getInstance().getPrefix() + ChatColor.RED + " Le joueur " + ChatColor.DARK_RED + args[1] + ChatColor.RED + " ne semble pas connecté !");
                        }
                        return true;
                    }
                }

                if(args.length == 3) {
                    if(args[0].contains("give")) {
                        final Player receiver = Bukkit.getPlayer(args[1]);

                        if(receiver.isOnline()) {
                            receiver.getInventory().addItem(Dynamite.getInstance().getDynamite());
                            receiver.updateInventory();
                        } else {
                            player.sendMessage(Dynamite.getInstance().getPrefix() + ChatColor.RED + " Le joueur " + ChatColor.DARK_RED + args[1] + ChatColor.RED + " ne semble pas connecté !");
                        }
                        return true;
                    }
                }


                player.sendMessage(Dynamite.getInstance().getPrefix() + ChatColor.GRAY + " Utilisation : /§cdynamite §7give <joueur> §6ou /§cdynamite");
                return true;
            } else {
                player.sendMessage(Dynamite.getInstance().getPrefix() + ChatColor.RED + " Vous n'avez pas la permission d'accéder à cette commande !" );
                return false;
            }
        }
        return false;
    }
}
