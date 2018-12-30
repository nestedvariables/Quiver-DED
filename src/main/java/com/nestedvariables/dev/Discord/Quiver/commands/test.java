package com.nestedvariables.dev.Discord.Quiver.commands;

import com.nestedvariables.dev.Discord.Quiver.Logger;
import com.nestedvariables.dev.Discord.Quiver.Utils;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class test extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + "testmsg")) {
            event.getChannel().sendMessage(Utils.getMessage(event.getGuild(), "testmsg")).queue();
        }
        else if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + "error")) {
            if (args[1].equalsIgnoreCase("fatal")) {
                Utils.getPrefix(event.getJDA().getGuildById("523301176309972993"));
            }
            else {
                Logger.log("fatal", "vadim went into blender", event.getGuild());
            }
        }
    }
}