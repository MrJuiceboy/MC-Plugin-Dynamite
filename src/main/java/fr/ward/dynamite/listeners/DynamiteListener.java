package fr.ward.dynamite.listeners;

import fr.ward.dynamite.Dynamite;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class DynamiteListener implements Listener {

    @EventHandler
    private void onGrenadeLaunch(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack dynamite = Dynamite.getInstance().getDynamite();

        if(hasDynamite(player.getItemInHand())) {
            if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                removeItems(player.getInventory(), dynamite, 1);
                player.updateInventory();
                launch(player, dynamite);
            }
        }
    }

    @EventHandler
    private void OnPlayerPickup(PlayerPickupItemEvent event) {
        final Item item = event.getItem();
        if(hasDynamite(item.getItemStack())) event.setCancelled(true);
    }

    @EventHandler
    private void OnPlayerDrop(PlayerDropItemEvent event) {
        final Item item = event.getItemDrop();
        if(hasDynamite(item.getItemStack())) event.setCancelled(true);
    }

    @EventHandler
    private void OnBlockPlace(BlockPlaceEvent event) {
        final ItemStack itemStack = event.getItemInHand();
        if(hasDynamite(itemStack)) event.setCancelled(true);
    }

    private void launch(Player player, ItemStack dynamite) {
        final float power = 10;
        final Item ball = player.getWorld().dropItem(player.getLocation(), new ItemStack(dynamite));
        final Location ballLocation = ball.getLocation();
        ball.setVelocity(player.getEyeLocation().getDirection());
        ball.getWorld().playSound(ballLocation, Sound.FUSE, 1L, 1L);

        Bukkit.getScheduler().runTaskLater(Dynamite.getInstance(), () -> {
            ball.getWorld().createExplosion(ball.getLocation(), power);
            ball.remove();
        }, 50L);
    }

    private void removeItems(Inventory inventory, ItemStack itemStack, int amount) {
        if (amount <= 0) return;
        int size = inventory.getSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack is = inventory.getItem(slot);
            if (is == null) continue;
            if (Objects.equals(itemStack.getItemMeta().getDisplayName(), is.getItemMeta().getDisplayName())) {
                int newAmount = is.getAmount() - amount;
                if (newAmount > 0) {
                    is.setAmount(newAmount);
                    break;
                } else {
                    inventory.clear(slot);
                    amount = -newAmount;
                    if (amount == 0) break;
                }
            }
        }
    }

    private boolean hasDynamite(ItemStack itemStack) {
        return itemStack.getItemMeta() != null &&
                itemStack.getItemMeta().hasDisplayName() &&
                itemStack.getItemMeta().getDisplayName().contains(Dynamite.getInstance().getPrefix());
    }
}
