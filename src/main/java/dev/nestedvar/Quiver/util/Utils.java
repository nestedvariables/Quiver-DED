package dev.nestedvar.Quiver.util;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Utils {
    static final SQLDriver sql = new SQLDriver();

    public static String getSelfAvatar(GuildMessageReceivedEvent event){
        return event.getJDA().getSelfUser().getEffectiveAvatarUrl();
    }

    public static String getPrefix(GuildMessageReceivedEvent event){
        String prefix = "Q!";
        try{
            Connection conn = sql.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `guilds` WHERE `guild_id`='" + event.getGuild().getId() + "'");

            while(rs.next()){
                prefix = rs.getString("prefix");
                conn.close();
                return prefix;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return prefix;
    }

    public static void setPrefix(GuildMessageReceivedEvent event, String prefix){
        try{
            Connection conn = sql.getConn();
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `guilds` SET `prefix`='" + prefix + "' WHERE `guild_id`='" + event.getGuild().getId() + "'");
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static int getColor(){

        Random obj = new Random();
        int random_number = obj.nextInt(0xffffff + 1);

        return random_number;
    }

}
