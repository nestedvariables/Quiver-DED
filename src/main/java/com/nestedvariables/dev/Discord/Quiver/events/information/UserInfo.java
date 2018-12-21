package com.nestedvariables.dev.Discord.Quiver.events.information;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.nestedvariables.dev.Discord.Quiver.Info;
import com.nestedvariables.dev.Discord.Quiver.SQLDriver;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class UserInfo extends ListenerAdapter {

    Connection sqlCon;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(Info.PREFIX + "userinfo")) {

            try { 
                Connection conn = SQLDriver.getConn();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("");

                while(rs.next());
            
            }catch(Exception e) {
            e.printStackTrace();
            }            

        }
    }
}