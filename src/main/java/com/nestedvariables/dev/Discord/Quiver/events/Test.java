package com.nestedvariables.dev.Discord.Quiver.events;

import com.nestedvariables.dev.Discord.Quiver.Info;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Test extends ListenerAdapter {


    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if(args[0].equalsIgnoreCase(Info.PREFIX + "test")) {
            event.getChannel().sendMessage("FUCK YOU!");
        }
    }

}