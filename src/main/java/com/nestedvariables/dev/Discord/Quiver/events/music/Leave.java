package com.nestedvariables.dev.Discord.Quiver.events.music;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import com.nestedvariables.dev.Discord.Quiver.Prefix;

public class Leave extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");

        if(args[0].equalsIgnoreCase(Prefix.getPrefix(event) + "leave") || args[0].equalsIgnoreCase(Prefix.getPrefix(event) + "fuckoff")){
            if(event.getGuild().getMemberById(event.getJDA().getSelfUser().getId()).getVoiceState()
            .inVoiceChannel() == false){
                event.getChannel().sendMessage("I'm not in a voice channel :(").queue();
            } else {
                event.getGuild().getAudioManager().closeAudioConnection();
            }
        }


    }

}