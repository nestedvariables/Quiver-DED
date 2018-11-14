package com.nestedvariables.dev.Discord.Quiver.events.owner;

import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class JoinAnnouncement extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        if(event.getMember().getUser().getId().equals(event.getJDA().getSelfUser().getId())){

            event.getGuild().getDefaultChannel().sendMessage("Hi! I'm " + event.getMember().getUser().getName() + ", and I'd like to thank you for inviting me to your discord server!");
            
        }else{
            return;
        }
    }

}