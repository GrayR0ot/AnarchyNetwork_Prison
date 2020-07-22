package eu.grayroot.anarchyprison;

import eu.grayroot.anarchyprison.listener.BlockListener;
import eu.grayroot.anarchyprison.listener.EnchantListener;
import eu.grayroot.anarchyprison.listener.LoginListener;
import eu.grayroot.anarchyprison.listener.gui.PickageGuiListener;
import eu.grayroot.anarchyprison.object.PrisonPlayer;
import eu.grayroot.anarchyprison.sql.PlayerData;
import eu.grayroot.anarchyprison.sql.SqlConnection;
import eu.grayroot.anarchyprison.utils.PrisonUtils;
import me.clip.ezblocks.EZBlocks;
import me.clip.ezprestige.EZPrestige;
import me.clip.ezrankspro.EZRanksPro;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class AnarchyPrison extends JavaPlugin {

    private static AnarchyPrison instance;
    private static SqlConnection sql;
    private static Connection sqlConnection;
    private static HashMap<Player, PrisonPlayer> prisonPlayers;
    private static Economy econ = null;

    @Override
    public void onEnable() {
        instance = this;
        prisonPlayers = new HashMap<>();
        sql = new SqlConnection("jdbc:mysql://", "127.0.0.1", 3306, "dbName", "dbUser", "dbPassword");
        try {
            sql.connect();
            log("Successfully connected to SQL Database!");
        } catch (SQLException | ClassNotFoundException e) {
            log("Unable to connect to Database! Closing the server");
            getServer().shutdown();
            e.printStackTrace();
        }
        sqlConnection = sql.getConnection();
        registerEvents();
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(!new PlayerData(AnarchyPrison.getInstance().getConnection()).isRegistered(player)) {
                new PlayerData(AnarchyPrison.getInstance().getConnection()).registerPlayer(player);
            }
            PrisonPlayer prisonPlayer = new PlayerData(AnarchyPrison.getInstance().getConnection()).getPlayerData(player);
            AnarchyPrison.getPrisonPlayers().put(player, prisonPlayer);
        }
        if (!setupEconomy() ) {
            log(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    @Override
    public void onDisable() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(!new PlayerData(AnarchyPrison.getInstance().getConnection()).isRegistered(player)) {
                new PlayerData(AnarchyPrison.getInstance().getConnection()).registerPlayer(player);
            } else {
                PrisonPlayer prisonPlayer = AnarchyPrison.getPrisonPlayers().get(player);
                new PlayerData(AnarchyPrison.getInstance().getConnection()).updatePlayerData(player, new PrisonUtils().getPlayerRank(player), getEconomy().getBalance(player), EZBlocks.getEZBlocks().getBlocksBroken(player), 0, prisonPlayer.getMana());
            }
        }
        prisonPlayers.clear();
        sql.disconnect();
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new LoginListener(), this);
        getServer().getPluginManager().registerEvents(new EnchantListener(), this);
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
        getServer().getPluginManager().registerEvents(new PickageGuiListener(), this);
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public void log(String log) {
        getLogger().info(log);
    }
    public SqlConnection getSql() {
        return sql;
    }
    public Connection getConnection() {
        return sqlConnection;
    }
    public static AnarchyPrison getInstance() {
        return instance;
    }
    public static Economy getEconomy() {
        return econ;
    }
    public static HashMap<Player, PrisonPlayer> getPrisonPlayers() {
        return prisonPlayers;
    }
}
