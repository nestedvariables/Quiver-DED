package com.nestedvariables.dev.Discord.Quiver.events.serverowner;

import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.Utils;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class LeaveGuild extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + "bye")) {
            event.getChannel().sendMessage("Ok I'll leave I see that I'm not wanted anymore :wave:").queue();

            event.getGuild().leave().queueAfter(5, TimeUnit.SECONDS);
        }
    }
}