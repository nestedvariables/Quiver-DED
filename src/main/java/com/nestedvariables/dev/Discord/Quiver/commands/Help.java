package com.nestedvariables.dev.Discord.Quiver.commands;

import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.Logger;
import com.nestedvariables.dev.Discord.Quiver.Utils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Help extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        
        if(args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + "help") || args[0].equalsIgnoreCase("q!help")){

            try{
                if(Utils.isBlacklisted(event)){

                    EmbedBuilder blacklisted = new EmbedBuilder();

                    blacklisted.setDescription(Utils.getMessage(event.getGuild(), "blacklistEmbedDescription").replace("{user}" , event.getMember().getAsMention()));
                    blacklisted.setColor(Utils.embedColor("error"));
                    blacklisted.setFooter(Utils.getMessage(event.getGuild(), "name") + " " + Utils.getMessage(event.getGuild(), "blacklistEmbedFooter"), event.getJDA().getSelfUser().getAvatarUrl());

                    event.getChannel().sendMessage(blacklisted.build()).queue((message) -> {
                        message.delete().queueAfter(15, TimeUnit.SECONDS);
                    });

                } else if(Utils.isBotOwner(event)){

                    EmbedBuilder botOwner = new EmbedBuilder();

                    botOwner.setTitle("Bot Owner Help");
                    botOwner.addField(Utils.getPrefix(event.getGuild()) + Utils.getMessage(event.getGuild(), "helpEmbedAnnounceTitle"), Utils.getMessage(event.getGuild(), "helpEmbedAnnounceDescription").replace("{prefix}" , Utils.getPrefix(event.getGuild())), false);
                    botOwner.addField(Utils.getPrefix(event.getGuild()) + Utils.getMessage(event.getGuild(), "helpEmbedBlacklistTitle"), Utils.getMessage(event.getGuild(), "helpEmbedBlacklistDescription").replace("{prefix}", Utils.getPrefix(event.getGuild())), false);
                    botOwner.addField(Utils.getPrefix(event.getGuild()) + Utils.getMessage(event.getGuild(), "helpEmbedUnblackTitle"),Utils.getMessage(event.getGuild(), "helpEmbedUnblacklistDescription").replace("{prefix}", Utils.getPrefix(event.getGuild())), false);


                } else if(Utils.isServerOwner(event)){
                    
                    EmbedBuilder serverOwner = new EmbedBuilder();

                } else if(Utils.isAdministrator(event)){

                } 

            } catch(Exception e) {
                Logger.log("fatal", e.toString(), event.getGuild());
            }

        }

    }
}