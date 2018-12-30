package com.nestedvariables.dev.Discord.Quiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Checks extends ListenerAdapter {

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
        if (event.getAuthor().getId().equals("237768953739476993")
                || event.getAuthor().getId().equals("79693184417931264")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBotOwner(MessageReceivedEvent event) {

        if (event.getAuthor().getId().equals("237768953739476993")
                || event.getAuthor().getId().equals("79693184417931264")) {
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

}