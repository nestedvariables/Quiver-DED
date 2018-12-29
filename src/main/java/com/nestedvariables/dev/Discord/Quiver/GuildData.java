package com.nestedvariables.dev.Discord.Quiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import net.dv8tion.jda.core.entities.Guild;

public class GuildData {

    public static HashMap<String, String> locales = new HashMap<>();
    public static HashMap<String, String> prefixes = new HashMap<>();
    
    // Fills up hashmaps on bot boot
    public static void loadData() {
        try {
            Connection connection = SQLDriver.getConn();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM `guild_options`");
                   
            if (!result.next()) {
                Logger.log("No guilds in database.");
            }
            else {
                while (result.next()) {
                    locales.put(result.getString("guild"), result.getString("locale"));
                    System.out.println(result.getString("guild") + "   " + result.getString("locale"));
                }
            }
        }
        catch (Exception e) {
            Logger.log(e.toString());
        }
    }

    // Return prefix for guild
    public static String getPrefix(Guild guild) {
        return prefixes.get(guild.getId());
    }

    // Set prefix for guild
    public static void setPrefix(Guild guild, String prefix) {

    }

    // Return embed color 
    public static Integer embedColor() {
        return 0x45f442;
    }
    
    // Return default locale
    public static String locale(String region) {
        System.out.println(region);
        switch (region) {
            case "us-east":
                return "en_US";
            case "us-west":
                return "en_US";
            case "us-central":
                return "en_US";
            case "us-south":
                return "en_US";
            case "brazil":
                return "pt_BR";
            case "eu-central":
                return "en_GB";
            case "hongkong":
                return "en_HK";
            case "japan":
                return "ja_JP";
            case "russia":
                return "ru_RU";
            case "singapore":
                return "en_SG";
            case "southafrica":
                return "en_ZA";
            case "sydney":
                return "en_AU";
            case "eu-west":
                return "en_GB";
            default:
                return null;
        }
    }
}