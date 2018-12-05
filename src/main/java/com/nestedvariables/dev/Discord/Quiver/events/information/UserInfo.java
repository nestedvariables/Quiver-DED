package com.nestedvariables.dev.Discord.Quiver.events.information;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.nestedvariables.dev.Discord.Quiver.Info;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class UserInfo extends ListenerAdapter {

    Connection sqlCon;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(Info.PREFIX + "userinfo")) {
            try { 
            Class.forName("com.mysql.jdbc.Driver");
            sqlCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiver", "root", "");
            Statement sqlStatement = sqlCon.createStatement();
            
            ResultSet sqlResult = sqlStatement.executeQuery("");
            
            
            }catch(Exception e) {
            e.printStackTrace();
            }            

        }
    }
}