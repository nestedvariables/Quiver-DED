package com.nestedvariables.dev.Discord.Quiver.commands;

import com.nestedvariables.dev.Discord.Quiver.GuildData;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class test extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase("~load")) {
            //event.getChannel().sendMessage(event.getGuild().getRegionRaw() + ": " + GuildData.locale(event.getGuild().getRegionRaw())).queue();
            GuildData.loadData();
            System.out.println(GuildData.locales);
        }
    }
}