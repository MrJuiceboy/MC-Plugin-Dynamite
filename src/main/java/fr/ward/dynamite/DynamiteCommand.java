package fr.ward.dynamite;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DynamiteCommand implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof final Player player) {

            if(args.length == 0) {
                if (player.isOp()) {
                    player.getInventory().addItem(Dynamite.getInstance().getDynamite());
                    player.updateInventory();
                    return true;
                }
            } else if(args.length == 2) {
                if(args[0].contains("give")) {
                    final Player receiver = Bukkit.getPlayer(args[1]);

                    if(receiver.isOnline()) {
                        receiver.getInventory().addItem(Dynamite.getInstance().getDynamite());
                        receiver.updateInventory();
                        return true;
                    }
                }
            }
            player.sendMessage(Dynamite.getInstance().getDynamiteName() + ChatColor.GRAY + " Utilisation : /§cdynamite §7give <joueur> §6ou /§cdynamite");
            return true;
        }
        return false;
    }
}
