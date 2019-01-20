package com.nestedvariables.dev.Discord.Quiver.events.announcements;

import java.util.Random;

import com.nestedvariables.dev.Discord.Quiver.Utils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MemberJoin extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Random random = new Random();
        int randomColor = random.nextInt(0xffffff + 1);
        EmbedBuilder join = new EmbedBuilder();
        join.setColor(randomColor);
        join.setThumbnail(event.getMember().getUser().getAvatarUrl());

        if (event.getMember().getUser().isBot()) {
            join.setTitle(":robot: New Bot!");
            join.addField("New bot's name", event.getMember().getAsMention(), false);
            join.setFooter("Quiver New Bot Welcome", Utils.getSelfAvatar(event));
        } 
        else {
            join.setTitle("New Member!");
            join.addField("New member's name", event.getMember().getAsMention(), false);
            join.setFooter("Quiver New Member Welcome", Utils.getSelfAvatar(event));
        }

        // Send and clear embed
        event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();
        join.clear();
    }
}