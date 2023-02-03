package fr.ward.dynamite;

import fr.ward.dynamite.commands.DynamiteCommand;
import fr.ward.dynamite.listeners.DynamiteListener;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Dynamite extends JavaPlugin {

    private final ItemStack dynamite = itemStack();
    private static Dynamite INSTANCE;

    public Dynamite() {
        INSTANCE = this;
    }
    public static Dynamite getInstance() {
        return INSTANCE;
    }
    public String getPrefix() {
        return "§7[§cDynamite§7]";
    }

    public ItemStack getDynamite() {
        return dynamite;
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onEnable() {
        INSTANCE = this;

        final PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new DynamiteListener(), this);

        this.getCommand("dynamite").setExecutor(new DynamiteCommand());

        super.onEnable();
    }

    @Override
    public void onDisable(){
        super.onDisable();
    }

    /*private ItemStack itemStack() {
        final String name = getPrefix();
        final ItemStack dynamite = new ItemStack(Material.BLAZE_ROD);
        final ItemMeta metaDynamite = dynamite.getItemMeta();
        metaDynamite.setDisplayName(name);
        dynamite.setItemMeta(metaDynamite);
        return dynamite;
    }*/

    private ItemStack itemStack() {
        final String name = getPrefix();
        final ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        final SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setDisplayName(name);
        skullMeta.setOwner("MHF_TNT2");
        skull.setItemMeta(skullMeta);
        return skull;
    }
}
