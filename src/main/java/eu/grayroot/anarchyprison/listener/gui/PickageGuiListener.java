package eu.grayroot.anarchyprison.listener.gui;

import eu.grayroot.anarchyprison.AnarchyPrison;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PickageGuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        final Player player = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        ItemStack current = e.getCurrentItem();
        if (inv.getName().equalsIgnoreCase("§8[§bEnchantement - " + AnarchyPrison.getPrisonPlayers().get(player).getMana() + " mana§8]")) {
            e.setCancelled(true);
            if (e.getClickedInventory() == null) return;
            if (current.hasItemMeta() && current.getItemMeta().hasDisplayName()) {
				int playerMana = AnarchyPrison.getPrisonPlayers().get(player).getMana();
				List lore = new ArrayList();
				lore.add("");
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				Date date = new Date();
				ItemStack currentItem = player.getItemInHand();
				ItemMeta currentItemMeta = currentItem.getItemMeta();
                switch (current.getItemMeta().getDisplayName()) {
					case "§bEfficacité":
						int efficiencyLevel = currentItem.getEnchantmentLevel(Enchantment.DIG_SPEED);
						if(playerMana >= 5) {
							AnarchyPrison.getPrisonPlayers().get(player).setMana(playerMana-5);
							lore.add("§7Enchanté le §b" + dateFormat.format(date) + " §7par §b" + player.getName());
							currentItemMeta.setLore(lore);
							currentItem.setItemMeta(currentItemMeta);
							currentItem.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiencyLevel+1);
							player.setItemInHand(currentItem);
							player.sendMessage("§8[§b!§8] §b» §7Item enchanté avec succès !");
							player.closeInventory();
						} else {
							player.sendMessage("§8[§c!§8] §c» §cVous ne disposez pas d'assez de mana !");
							player.closeInventory();
						}
						break;

					case "§bFortune":
						int fortuneLevel = currentItem.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
						if(playerMana >= 15) {
							AnarchyPrison.getPrisonPlayers().get(player).setMana(playerMana-15);
							lore.add("§7Enchanté le §b" + dateFormat.format(date) + " §7par §b" + player.getName());
							currentItemMeta.setLore(lore);
							currentItem.setItemMeta(currentItemMeta);
							currentItem.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, fortuneLevel+1);
							player.setItemInHand(currentItem);
							player.sendMessage("§8[§b!§8] §b» §7Item enchanté avec succès !");
							player.closeInventory();
						} else {
							player.sendMessage("§8[§c!§8] §c» §cVous ne disposez pas d'assez de mana !");
							player.closeInventory();
						}
						break;

                    default:
                        break;

                }
            }
        }
    }
}