package com.nestedvariables.dev.Discord.Quiver.events.owner;

import com.nestedvariables.dev.Discord.Quiver.Logger;
import com.nestedvariables.dev.Discord.Quiver.Utils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ErrorTest extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        
        if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + "error")) {
            try{
            if (Utils.isBotOwner(event.getAuthor())) {
                
                EmbedBuilder error = new EmbedBuilder();

                error.setTitle(Utils.getMessage(event.getGuild(), "roleCreateErrorEmbedTitle"));
                error.setDescription(Utils.getMessage(event.getGuild(), "roleCreateErrorEmbedDescription"));
                error.setFooter(Utils.getMessage(event.getGuild(), "name") + " " + Utils.getMessage(event.getGuild(), "roleCreateErrorEmbedFooter"), event.getJDA().getSelfUser().getAvatarUrl());

                event.getChannel().sendMessage(error.build()).queue();
            }
        }catch (Exception e){
            Logger.log("fatal", e.toString(), event.getGuild());
        }

        }
    }
}