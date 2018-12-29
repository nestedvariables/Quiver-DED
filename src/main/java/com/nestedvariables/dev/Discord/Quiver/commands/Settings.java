package com.nestedvariables.dev.Discord.Quiver.commands;

import com.nestedvariables.dev.Discord.Quiver.GuildData;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Settings extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(GuildData.getPrefix(event.getGuild()) + "set")) {
            if (args.length < 2) {
                // Send help (i mean help embed not like mental help)
            }
            else if (args[1].equalsIgnoreCase(GuildData.getPrefix(event.getGuild()) + "prefix")) {
                if (args.length < 3) {
                    // Send prefix usage
                }
                else {
                    GuildData.setPrefix(event.getGuild(), args[2]);
                }   
            }
        }
    }
}