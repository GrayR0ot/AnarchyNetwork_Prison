package eu.grayroot.anarchyprison.gui;

import eu.grayroot.anarchyprison.AnarchyPrison;
import eu.grayroot.anarchyprison.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PickaxeGui {

	public PickaxeGui() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void openGui(Player player) {
		Inventory menu = Bukkit.createInventory(null, 27, "§8[§bEnchantement - " + AnarchyPrison.getPrisonPlayers().get(player).getMana() + " mana§8]");
		ItemStack playerItem = player.getItemInHand();
		menu.setItem(11, new ItemBuilder(Material.SUGAR,1).setName("§bEfficacité")
						.setLore("",
								"§f§l➥ §7Niveau actuel: §f" + playerItem.getEnchantmentLevel(Enchantment.DIG_SPEED),
								"§f§l➥ §7Prix par niveau: §f5",
								"§f§l➥ §7Clique ici pour enchanter")
						.setHidenItemFlags().toItemStack());
		menu.setItem(15, new ItemBuilder(Material.HOPPER,1).setName("§bFortune")
				.setLore("",
						"§f§l➥ §7Niveau actuel: §f" + playerItem.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS),
						"§f§l➥ §7Prix par niveau: §f15",
						"§f§l➥ §7Clique ici pour enchanter")
				.setHidenItemFlags().toItemStack());

		ItemStack Background = new ItemStack (Material.STAINED_GLASS_PANE, 1, DyeColor.WHITE.getDyeData());
		for(int i = 0; i < 27; i++) {
			if(menu.getItem(i) == null || menu.getItem(i).getType() == Material.AIR) {
				menu.setItem(i, Background);
			}
		}
		player.openInventory(menu);
	}

}
