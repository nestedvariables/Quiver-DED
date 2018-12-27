package com.nestedvariables.dev.Discord.Quiver.events.owner;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CodeBlock extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        if(event.getMessage().getContentRaw().startsWith("```") && event.getMessage().getContentRaw().endsWith("```") && event.getMessage().getContentRaw().length() > 200){
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("NO CODE BLOCK").queue();
        }
    }
}