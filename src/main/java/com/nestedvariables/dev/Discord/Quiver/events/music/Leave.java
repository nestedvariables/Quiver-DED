package com.nestedvariables.dev.Discord.Quiver.events.music;

import com.nestedvariables.dev.Discord.Quiver.Utils;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Leave extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + "leave") || args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + "fuckoff")){
            if(event.getGuild().getMemberById(event.getJDA().getSelfUser().getId()).getVoiceState()
            .inVoiceChannel() == false){
                event.getChannel().sendMessage("I'm not in a voice channel :(").queue();
            } else {
                event.getGuild().getAudioManager().closeAudioConnection();
            }
        }


    }

}