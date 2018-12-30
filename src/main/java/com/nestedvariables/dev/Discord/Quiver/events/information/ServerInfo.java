package com.nestedvariables.dev.Discord.Quiver.events.information;

import com.nestedvariables.dev.Discord.Quiver.Utils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ServerInfo extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + "serverinfo")){
            EmbedBuilder eb = new EmbedBuilder();
            
            
        }
    } 

}