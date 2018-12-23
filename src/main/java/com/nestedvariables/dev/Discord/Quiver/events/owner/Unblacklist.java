package com.nestedvariables.dev.Discord.Quiver.events.owner;

import com.nestedvariables.dev.Discord.Quiver.Bools;
import com.nestedvariables.dev.Discord.Quiver.Info;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Unblacklist extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(Info.PREFIX + "unblacklist")) {
            if(Bools.isBotOwner(event)) {
                
            }
        }
    }
}