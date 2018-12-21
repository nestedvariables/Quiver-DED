package com.nestedvariables.dev.Discord.Quiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Prefix extends ListenerAdapter{

    public static String getPrefix(GuildMessageReceivedEvent event){
        String prefix = "";
        try{            
            Connection conn = SQLDriver.getConn();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM `guild_options` WHERE `guild_id`=" + event.getGuild().getId());

            while(rs.next())
            prefix = rs.getString("`guild_prefix`");

        } catch(SQLException e){
            System.out.print("Grabbing the prefix for guild: " + event.getGuild().getName() + " failed\n" + e.toString() + "\n");
        }
        return prefix;
    }

}