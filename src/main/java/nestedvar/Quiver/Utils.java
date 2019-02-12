package nestedvar.Quiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Random;

import nestedvar.Quiver.util.Logger;
import nestedvar.Quiver.util.SQL;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

@Deprecated
public class Utils {

    public static HashMap<String, String> locales = new HashMap<>();
    public static HashMap<String, String> prefixes = new HashMap<>();
    public static HashMap<String, String> logChannels = new HashMap<>();
    public static HashMap<String, String> loggerMessages = new HashMap<>();

    // Return log channel for guild
    public static String getLogChannel(Guild guild) {
        return logChannels.get(guild.getId());
    }

    // Return embed color 
    public static Integer embedColor(String type) {
        switch (type) {
            case "warning":
                return 0xffc83f;
            case "success":
                return 0x3fff6f;
            case "codeBlock":
                return 0xa06926;
            case "error":
                return 0xff4032;
            case "info":
                return 0x5eccff;
            case "random" :
                Random random = new Random();
                Integer randomColor = random.nextInt(0xffffff + 1);
                return randomColor;
            default:
                return 0x45f442;
        }
    }
    
    public static boolean isChannelSystemEnabled(Guild guild) {
        String channelSystemEnabled = "true";        

        try{
            Connection conn = SQL.getConn();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM `guild_options` WHERE `guild_id`=" + guild.getId().toString());

            while(rs.next())
              channelSystemEnabled = rs.getString(3);

        } catch (SQLException sqle) {
            new Logger(1, sqle.toString());
        }

        if(channelSystemEnabled == "true") {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBlacklisted(User author) {

        String blacklistID = null;

        try {
            Connection conn = SQL.getConn();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM `blacklist` WHERE `discord_id`=" + author.getId().toString());
            while (rs.next())
                blacklistID = rs.getString("discord_id");

        } catch (SQLException sqle) {
            new Logger(1, sqle.toString());
        }

        if (blacklistID != null) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isBotOwner(User author) {
        if (author.getId().equals("237768953739476993") || author.getId().equals("79693184417931264")) {
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
            Connection connection = SQL.getConn();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM `premium`");
                   
            if (!result.next()) {
                new Logger(2, "No premium users in database."); 
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
            new Logger(1, e.toString());
            return false;
        }
    }

    public static String getSelfMention(GuildMessageReceivedEvent event){ 
        return event.getJDA().getSelfUser().getAsMention();
    }

    public static String getSelfMention(MessageReceivedEvent event){ 
        return event.getJDA().getSelfUser().getAsMention();
    }

    public static String getSelfAvatar(GuildMemberJoinEvent event) {
        return event.getJDA().getSelfUser().getAvatarUrl();
    }

    public static String getSelfAvatar(GuildMessageReceivedEvent event) {
        return event.getJDA().getSelfUser().getAvatarUrl();
    }

    public static String getSelfAvatar(MessageReceivedEvent event) {
        return event.getJDA().getSelfUser().getAvatarUrl();
    }

}