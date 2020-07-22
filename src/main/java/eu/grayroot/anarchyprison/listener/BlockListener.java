package eu.grayroot.anarchyprison.listener;

import eu.grayroot.anarchyprison.AnarchyPrison;
import eu.grayroot.anarchyprison.object.PrisonPlayer;
import eu.grayroot.anarchyprison.sql.PlayerData;
import me.clip.ezblocks.EZBlocks;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BlockListener implements Listener {

    @EventHandler
    public void onJoin(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (String.valueOf(EZBlocks.getEZBlocks().getBlocksBroken(player)).contains("0000")) {
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
            AnarchyPrison.getPrisonPlayers().get(player).setMana(AnarchyPrison.getPrisonPlayers().get(player).getMana()+1);
            player.sendMessage("§8[§b!§8] §b» §7Bonus §b10 000 §7blocs minés ! (§b+1 Mana§7)");
        }

    }
}
