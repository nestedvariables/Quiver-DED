package com.nestedvariables.dev.Discord.Quiver;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;

public class Utils {

    public static HashMap<String, String> locales = new HashMap<>();
    public static HashMap<String, String> prefixes = new HashMap<>();
    
    // Fills up hashmaps on bot boot
    public static void loadData() {
        try {
            Connection connection = SQLDriver.getConn();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM `guild_options`");
                   
            if (!result.next()) {
                Logger.log("warning", "No guilds in database.", null, null);
            }
            else {
                while (result.next()) {
                    locales.put(result.getString("guild"), result.getString("locale"));
                    prefixes.put(result.getString("guild"), result.getString("prefix"));
                }
            }
            statement.close();
        }
        catch (Exception e) {
            Logger.log("fatal", e.toString(), null, null);
        }
    }

    // Return prefix for guild
    public static String getPrefix(Guild guild) {
        return prefixes.get(guild.getId());
    }

    // Set prefix for guild
    public static void setPrefix(Guild guild, Channel channel, String prefix) {
        try {
            Connection connection = SQLDriver.getConn();
            Statement statement = connection.createStatement();
            statement.execute("UPDATE `guild_options` SET `prefix`='" + prefix + "' WHERE `guild`='" + guild.getId() + "'");
            prefixes.put(guild.getId(), prefix);
            statement.close();
        }
        catch (Exception e) {
            Logger.log("fatal", e.toString(), guild, channel);
        }
    }

    // Return embed color 
    public static Integer embedColor(String type) {
        switch (type) {
            case "usage":
                return 0xffc83f;
            case "success":
                return 0x3fff6f;
            case "codeBlock":
                return 0xa06926;
            default:
                return 0x45f442;
        }
    }

    // Get message with proper locale
    public static String getMessage(Channel channel, String message) {
        JSONParser parser = new JSONParser();
        try {
            InputStream in = Utils.class.getResourceAsStream("locale/" + Utils.getLocale(channel.getGuild()) + ".json");
            Object object = parser.parse(IOUtils.toString(in, "UTF-8"));
            JSONObject json = (JSONObject) object;
            in.close();
            return (String) json.get(message);
        } 
        catch (Exception e) {
            e.printStackTrace();
            Logger.log("fatal", e.toString(), channel.getGuild(), channel);
            return null;
        }
    }

    // Set locale
    public static void setLocale(Channel channel, String locale) {
        try {
            Connection connection = SQLDriver.getConn();
            Statement statement = connection.createStatement();
            statement.execute("UPDATE `guild_options` SET `locale`='" + locale + "' WHERE `guild`='" + channel.getGuild().getId() + "'");
            locales.put(channel.getGuild().getId(), locale);
            statement.close();
        }
        catch (Exception e) {
            Logger.log("fatal", e.toString(), channel.getGuild(), channel);
        }
    } 
    
    // Get locale for guild
    public static String getLocale(Guild guild) {
        return locales.get(guild.getId());
    }

    // Return default locale
    public static String locale(String region) {
        //switch (region) {
            if (region.equals("us-central")) {
                return "en_US";
            }
            else {
                return null;
            }
            /*
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
        } */
    }
}