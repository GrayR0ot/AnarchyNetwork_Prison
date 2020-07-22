package eu.grayroot.anarchyprison.listener;

import eu.grayroot.anarchyprison.AnarchyPrison;
import eu.grayroot.anarchyprison.object.PrisonPlayer;
import eu.grayroot.anarchyprison.sql.PlayerData;
import eu.grayroot.anarchyprison.utils.PrisonUtils;
import me.clip.ezblocks.EZBlocks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LoginListener implements Listener {

    @EventHandler
    public void onJoin(PlayerLoginEvent e) {
        Player player = e.getPlayer();
        if(!new PlayerData(AnarchyPrison.getInstance().getConnection()).isRegistered(player)) {
            new PlayerData(AnarchyPrison.getInstance().getConnection()).registerPlayer(player);
        }
        PrisonPlayer prisonPlayer = new PlayerData(AnarchyPrison.getInstance().getConnection()).getPlayerData(player);
        AnarchyPrison.getPrisonPlayers().put(player, prisonPlayer);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if(!new PlayerData(AnarchyPrison.getInstance().getConnection()).isRegistered(player)) {
            new PlayerData(AnarchyPrison.getInstance().getConnection()).registerPlayer(player);
        } else {
            PrisonPlayer prisonPlayer = AnarchyPrison.getPrisonPlayers().get(player);
            new PlayerData(AnarchyPrison.getInstance().getConnection()).updatePlayerData(player, new PrisonUtils().getPlayerRank(player), AnarchyPrison.getEconomy().getBalance(player), EZBlocks.getEZBlocks().getBlocksBroken(player), 0, prisonPlayer.getMana());
        }
    }
}
