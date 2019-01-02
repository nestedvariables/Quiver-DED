package com.nestedvariables.dev.Discord.Quiver;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Utils {

    public static HashMap<String, String> locales = new HashMap<>();
    public static HashMap<String, String> prefixes = new HashMap<>();
    public static HashMap<String, String> logChannels = new HashMap<>();

    // Fills up hashmaps on bot boot
    public static void loadData() {
        try {
            Connection connection = SQLDriver.getConn();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM `guild_options`");
                   
            if (!result.next()) {
                Logger.log("warning", "No guilds in database.", null);
            }
            else {
                while (result.next()) {
                    locales.put(result.getString("guild"), result.getString("locale"));
                    prefixes.put(result.getString("guild"), result.getString("prefix"));
                    logChannels.put(result.getString("guild"), result.getString("log_channel"));
                }
            }
            statement.close();
        }
        catch (Exception e) {
            Logger.log("fatal", e.toString(), null);
        }
    }

    // Return prefix for guild
    public static String getPrefix(Guild guild) {
        return prefixes.get(guild.getId());
    }

    // Set prefix for guild
    public static void setPrefix(Guild guild, String prefix) {
        try {
            Connection connection = SQLDriver.getConn();
            Statement statement = connection.createStatement();
            statement.execute("UPDATE `guild_options` SET `prefix`='" + prefix + "' WHERE `guild`='" + guild.getId() + "'");
            prefixes.put(guild.getId(), prefix);
            statement.close();
        }
        catch (Exception e) {
            Logger.log("fatal", e.toString(), guild);
        }
    }

    // Return log channel for guild
    public static String getLogChannel(Guild guild) {
        return logChannels.get(guild.getId());
    }

    // Set log channel for guild
    public static void setLogChannel(Guild guild, String channelId) {
        try {
            Connection connection = SQLDriver.getConn();
            Statement statement = connection.createStatement();
            statement.execute("UPDATE `guild_options` SET `log_channel`='" + channelId + "' WHERE `guild`='" + guild.getId() + "'");
            logChannels.put(guild.getId(), channelId);
            statement.close();
        }
        catch (Exception e) {
            Logger.log("fatal", e.toString(), guild);
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
    public static String getMessage(Guild guild, String message) {
        JSONParser parser = new JSONParser();
        try {
            InputStream in = Utils.class.getResourceAsStream("locale/" + Utils.getLocale(guild) + ".json");
            Object object = parser.parse(IOUtils.toString(in, "UTF-8"));
            JSONObject json = (JSONObject) object;
            in.close();
            return (String) json.get(message);
        } 
        catch (Exception e) {
            Logger.log("fatal", String.valueOf(e), guild);
            return null;
        }
    }

    // Set locale
    public static void setLocale(Guild guild, String locale) {
        try {
            Connection connection = SQLDriver.getConn();
            Statement statement = connection.createStatement();
            statement.execute("UPDATE `guild_options` SET `locale`='" + locale + "' WHERE `guild`='" + guild.getId() + "'");
            locales.put(guild.getId(), locale);
            statement.close();
        }
        catch (Exception e) {
            Logger.log("fatal", e.toString(), guild);
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

    public static boolean isChannelSystemEnabled(GuildMessageReceivedEvent event) {
        String channelSystemEnabled = "true";        

        try{
            Connection conn = SQLDriver.getConn();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM `guild_options` WHERE `guild_id`=" + event.getGuild().getId().toString());

            while(rs.next())
              channelSystemEnabled = rs.getString(3);

        } catch (SQLException sqle) {
            event.getJDA().getGuildById("488137783127572491").getTextChannelById("517756124846358529").sendMessage(event.getJDA().getGuildById("488137783127572491").getRoleById("489269871306080257").getAsMention() + "\n" + sqle.toString()).queue();
        }

        if(channelSystemEnabled == "true") {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBlacklisted(GuildMessageReceivedEvent event) {

        String blacklistID = null;

        try {
            Connection conn = SQLDriver.getConn();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM `blacklist` WHERE `discord_id`=" + event.getAuthor().getId().toString());
            while (rs.next())
                blacklistID = rs.getString("discord_id");

        } catch (SQLException sqle) {
            event.getJDA().getGuildById("488137783127572491").getTextChannelById("517756124846358529").sendMessage(event.getJDA().getGuildById("488137783127572491").getRoleById("489269871306080257").getAsMention() + "\n" + sqle.toString()).queue();
        }

        if (blacklistID != null) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isBlacklisted(MessageReceivedEvent event) {

        String blacklistID = null;

        try {
            Connection conn = SQLDriver.getConn();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM `blacklist` WHERE `discord_id`=" + event.getAuthor().getId().toString());
            while (rs.next())
                blacklistID = rs.getString("discord_id");

        } catch (SQLException sqle) {
            event.getJDA().getGuildById("488137783127572491").getTextChannelById("517756124846358529").sendMessage(event.getJDA().getGuildById("488137783127572491").getRoleById("489269871306080257").getAsMention() + "\n" + sqle.toString()).queue();
        }

        if (blacklistID != null) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isBotOwner(GuildMessageReceivedEvent event) {
        if (event.getAuthor().getId().equals("237768953739476993") || event.getAuthor().getId().equals("79693184417931264")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBotOwner(MessageReceivedEvent event) {

        if (event.getAuthor().getId().equals("237768953739476993") || event.getAuthor().getId().equals("79693184417931264")) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isServerOwner(GuildMessageReceivedEvent event) {
        if (event.getMember().isOwner()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isAdministrator(GuildMessageReceivedEvent event) {
        if (event.getMember().hasPermission(Permission.ADMINISTRATOR)){
            return true;            
        } else {
            return false;
        }
    }

    public static boolean isPremium(Guild guild) {
        try {
            Connection connection = SQLDriver.getConn();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM `premium`");
                   
            if (!result.next()) {
                Logger.log("warning", "No premium users in database.", null);
                return false;
            }
            else {
                while (result.next()) {
                    if (guild.getOwner().getUser().getId().equals(result.getString("user"))) {
                        statement.close();
                        return true;
                    }
                }
            }
            statement.close();
            return false;
        }
        catch (Exception e) {
            Logger.log("fatal", e.toString(), null);
            return false;
        }
    }
}