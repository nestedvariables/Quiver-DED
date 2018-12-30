package com.nestedvariables.dev.Discord.Quiver.commands;

import com.nestedvariables.dev.Discord.Quiver.Utils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Settings extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + "set")) {
            if (args.length < 2) {
                Em
                
            }
            else if (args[1].equalsIgnoreCase("prefix")) {
                if (args.length < 3) {
                    event.getChannel().sendMessage("Usage: `!set prefix 'yourprefix'`").queue();
                }
                else {
                    Utils.setPrefix(event.getGuild(), args[2]);
                    event.getChannel().sendMessage("Set prefix. Current prefix is " + Utils.getPrefix(event.getGuild())).queue();
                }   
            }
            else if (args[1].equalsIgnoreCase("locale")) {
                if (args.length < 3) {
                    event.getChannel().sendMessage("Usage: `!set locale 'locale'`").queue();
                }
                else {
                    Utils.setLocale(event.getGuild(), args[2]);
                    event.getChannel().sendMessage(Utils.getMessage(event.getGuild(), "setlocale").replace("{locale}", Utils.getLocale(event.getGuild()))).queue();
                }   
            }
        }
    }
}