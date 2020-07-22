package eu.grayroot.anarchyprison.sql;

import eu.grayroot.anarchyprison.AnarchyPrison;
import eu.grayroot.anarchyprison.object.PrisonPlayer;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerData {

    private Connection connection;

    public PlayerData(Connection connection) {
        this.connection = AnarchyPrison.getInstance().getConnection();
    }

    public void registerPlayer(Player player){
        try {
            PreparedStatement rs = connection.prepareStatement("INSERT INTO `prison_players` (name, uuid) VALUES (?, ?)");
            rs.setString(1, player.getName());
            rs.setString(2, player.getUniqueId().toString());
            rs.executeUpdate();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isRegistered(Player player) {
        try {
            PreparedStatement q = connection.prepareStatement("SELECT `uuid` FROM `prison_players` WHERE `uuid` = ?");
            q.setString(1, player.getUniqueId().toString());
            ResultSet resultat = q.executeQuery();
            boolean isRegistered = resultat.next();
            q.close();
            return isRegistered;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



    public PrisonPlayer getPlayerData(Player player) {
        PrisonPlayer prisonPlayer = null;
        try {
            PreparedStatement q = connection.prepareStatement("SELECT * FROM `prison_players` WHERE `uuid` = ?");
            q.setString(1, player.getUniqueId().toString());
            ResultSet rs = q.executeQuery();
            while(rs.next()) {
                prisonPlayer = new PrisonPlayer(rs.getInt("id"), rs.getString("name"), rs.getString("uuid"), rs.getString("rank"), rs.getDouble("balance"), rs.getInt("blocks"), rs.getInt("prestige"), rs.getInt("mana"));
            }
            q.close();
            return prisonPlayer;

        } catch (SQLException e) {
            e.printStackTrace();
            return prisonPlayer;
        }
    }

    public void updatePlayerData(Player player, String rank, double balance, int blocks, int prestige, int mana){
        try {
            PreparedStatement rs = connection.prepareStatement("UPDATE `prison_players` SET `rank` =?, `balance`=?, `blocks`=?, `prestige`=?, `mana`=? WHERE `uuid`=?");
            rs.setString(1, rank);
            rs.setDouble(2, balance);
            rs.setInt(3, blocks);
            rs.setInt(4, prestige);
            rs.setInt(5, mana);
            rs.setString(6, player.getUniqueId().toString());
            rs.executeUpdate();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
