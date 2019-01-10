package com.nestedvariables.dev.Discord.Quiver.events.owner;

import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.Utils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class List extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + Utils.getMessage(event.getGuild(), "list"))){

            if(args.length < 2) {
                EmbedBuilder nullArgs = new EmbedBuilder();

                nullArgs.setDescription(Utils.getMessage(event.getGuild(), "listNullArgsDescription"));
                nullArgs.setColor(Utils.embedColor("error"));
                nullArgs.setFooter(Utils.getMessage(event.getGuild(), "name") + " " + Utils.getMessage(event.getGuild(), "listNullArgsFooter"), event.getJDA().getSelfUser().getAvatarUrl());

                event.getChannel().sendMessage(nullArgs.build()).queue();
            }
        }
    }

}