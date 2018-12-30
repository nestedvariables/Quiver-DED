package com.nestedvariables.dev.Discord.Quiver.commands;

import com.nestedvariables.dev.Discord.Quiver.Logger;
import com.nestedvariables.dev.Discord.Quiver.Utils;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class test extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getChannel()) + "testmsg")) {
            event.getChannel().sendMessage(Utils.getMessage(event.getChannel(), "testmsg")).queue();
        }
        else if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getChannel()) + "error")) {
            if (args[1].equalsIgnoreCase("fatal")) {
                Utils.setPrefix(null, null, null);
            }
            else {
                Logger.log("warning", "vadim went into blender", event.getGuild(), event.getChannel());
            }
        }
    }
}