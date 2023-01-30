package fr.ward.dynamite;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class DynamiteListener implements Listener {

    @EventHandler
    private void onGrenadeLaunch(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemStack = event.getItem();

        final ItemStack dynamite = Dynamite.getInstance().getDynamite();

        if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if (itemStack.equals(dynamite)) {
                player.getInventory().removeItem(new ItemStack(dynamite));
                player.updateInventory();
                launch(player, dynamite);
            }
        }
    }

    @EventHandler
    private void OnPlayerPickup(PlayerPickupItemEvent event) {
        final Item item = event.getItem();
        final ItemStack dynamite = Dynamite.getInstance().getDynamite();
        if(item.getItemStack().equals(dynamite)) event.setCancelled(true);
    }

    @EventHandler
    private void OnPlayerDrop(PlayerDropItemEvent event) {
        final Item item = event.getItemDrop();
        final ItemStack dynamite = Dynamite.getInstance().getDynamite();
        if(item.getItemStack().equals(dynamite)) event.setCancelled(true);
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
}
