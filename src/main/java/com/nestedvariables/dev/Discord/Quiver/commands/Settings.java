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
                event.getChannel().sendMessage("no arg1").queue();
            }
            else if (args[1].equalsIgnoreCase("prefix")) {
                if (args.length < 3) {
                    event.getChannel().sendMessage("Usage: `!set prefix 'yourprefix'`").queue();
                }
                else {
                    GuildData.setPrefix(event.getGuild(), args[2]);
                    event.getChannel().sendMessage("Set prefix. Current prefix is " + GuildData.getPrefix(event.getGuild())).queue();
                }   
            }
            else if (args[1].equalsIgnoreCase("locale")) {
                if (args.length < 3) {
                    event.getChannel().sendMessage("Usage: `!set locale 'locale'`").queue();
                }
                else {
                    GuildData.setLocale(event.getGuild(), args[2]);
                    event.getChannel().sendMessage(GuildData.getMessage(event.getGuild(), "setlocale").replace("{locale}", GuildData.getLocale(event.getGuild()))).queue();
                }   
            }
        }
    }
}