package fr.ward.dynamite.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MineLogger {
    private static void sendMessage(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(message);
    }

    public static void info(String message) {
        sendMessage(ChatColor.BLUE + "[INFO] " + message);
    }

    public static void warning(String message) {
        sendMessage(ChatColor.YELLOW + "[WARNING] " + message);
    }

    public static void error(String message) {
        sendMessage(ChatColor.RED + "[ERROR] " + message);
    }
}
