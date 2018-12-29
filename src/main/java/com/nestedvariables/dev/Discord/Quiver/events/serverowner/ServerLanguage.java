package com.nestedvariables.dev.Discord.Quiver.events.serverowner;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.nestedvariables.dev.Discord.Quiver.Prefix;
import com.nestedvariables.dev.Discord.Quiver.SQLDriver;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ServerLanguage extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(Prefix.getPrefix(event) + "setlang")) {

            try {
                Connection conn = SQLDriver.getConn();
                Statement stmt = conn.createStatement();

                

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}