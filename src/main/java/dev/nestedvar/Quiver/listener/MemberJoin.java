package dev.nestedvar.Quiver.listener;

import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MemberJoin extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        if(event.getMember().getUser().isBot() || event.getMember().getUser().isFake()){

        } else {
            
        }
    }

}
