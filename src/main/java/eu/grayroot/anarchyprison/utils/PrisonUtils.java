package eu.grayroot.anarchyprison.utils;

import org.bukkit.entity.Player;

public class PrisonUtils {

    public PrisonUtils() {
        super();
    }

    public String getPlayerRank(Player player) {
        if(player.hasPermission("Free")) {
            return "Free";
        } else if(player.hasPermission("Z")) {
            return "Z";
        } else if(player.hasPermission("Y")) {
            return "Y";
        } else if(player.hasPermission("X")) {
            return "X";
        } else if(player.hasPermission("W")) {
            return "W";
        } else if(player.hasPermission("V")) {
            return "V";
        } else if(player.hasPermission("U")) {
            return "U";
        } else if(player.hasPermission("T")) {
            return "T";
        } else if(player.hasPermission("S")) {
            return "S";
        } else if(player.hasPermission("R")) {
            return "R";
        } else if(player.hasPermission("Q")) {
            return "Q";
        } else if(player.hasPermission("P")) {
            return "P";
        } else if(player.hasPermission("O")) {
            return "O";
        } else if(player.hasPermission("N")) {
            return "N";
        } else if(player.hasPermission("M")) {
            return "M";
        } else if(player.hasPermission("L")) {
            return "L";
        } else if(player.hasPermission("K")) {
            return "K";
        } else if(player.hasPermission("J")) {
            return "J";
        } else if(player.hasPermission("I")) {
            return "I";
        } else if(player.hasPermission("H")) {
            return "H";
        } else if(player.hasPermission("G")) {
            return "G";
        } else if(player.hasPermission("F")) {
            return "F";
        } else if(player.hasPermission("E")) {
            return "E";
        } else if(player.hasPermission("D")) {
            return "D";
        } else if(player.hasPermission("C")) {
            return "C";
        } else if(player.hasPermission("B")) {
            return "B";
        } else {
            return "A";
        }
    }
}
