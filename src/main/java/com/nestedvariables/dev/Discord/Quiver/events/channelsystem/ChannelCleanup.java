package com.nestedvariables.dev.Discord.Quiver.events.channelsystem;

import java.util.List;

import com.nestedvariables.dev.Discord.Quiver.Info;

import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ChannelCleanup extends ListenerAdapter {

    public void onVoiceLeave(GuildVoiceLeaveEvent event){
        List<Member> membersPrivChannel = event.getChannelLeft().getMembers();
        if(event.getChannelLeft().getParent().equals(event.getGuild().getCategoriesByName("Private Channels", true).get(0)) && membersPrivChannel.size() < 1){
           event.getChannelLeft().getManager().getChannel().delete().queue();           
        }
    }
}