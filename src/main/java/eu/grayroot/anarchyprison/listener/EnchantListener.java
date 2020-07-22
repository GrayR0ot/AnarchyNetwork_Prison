package eu.grayroot.anarchyprison.listener;

import eu.grayroot.anarchyprison.gui.PickaxeGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnchantListener implements Listener {

    @EventHandler
    public void onEnchantGui(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (player.getItemInHand() != null && player.getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                new PickaxeGui().openGui(player);
            }
        }
    }
}
