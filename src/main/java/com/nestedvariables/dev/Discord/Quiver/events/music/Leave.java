package com.nestedvariables.dev.Discord.Quiver.events.music;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import com.nestedvariables.dev.Discord.Quiver.Info;

public class Leave extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");

        if(args[0].equalsIgnoreCase(Info.PREFIX)){
            
        }


    }

}