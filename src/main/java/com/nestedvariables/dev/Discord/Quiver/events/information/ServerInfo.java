package com.nestedvariables.dev.Discord.Quiver.events.information;

import com.nestedvariables.dev.Discord.Quiver.Utils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ServerInfo extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + Utils.getMessage(event.getGuild(), "serverInfoCommand"))){
            Integer memberCount = event.getGuild().getMembers().size();
            Integer shardID = event.getJDA().getShardInfo().getShardId();
            EmbedBuilder eb = new EmbedBuilder();
            
            eb.setTitle(Utils.getMessage(event.getGuild(), "guildInformationEmbedTitle").replace("{guild}", event.getGuild().getName()));
            eb.setDescription(Utils.getMessage(event.getGuild(), "guildInformationEmbedDescription").replace("{guild}", event.getGuild().getName()).replace("{memberCount}",  memberCount.toString()).replace("{shardID}", shardID.toString()));
            eb.setFooter(Utils.getMessage(event.getGuild(), "name") + " " + Utils.getMessage(event.getGuild(), "guildInformationEmbedFooter"), event.getJDA().getSelfUser().getAvatarUrl());
            
        }
    } 

}