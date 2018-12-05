package com.nestedvariables.dev.Discord.Quiver.events.announcements;

import java.util.Random;

import com.nestedvariables.dev.Discord.Quiver.Info;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MemberJoin extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        if (event.getMember().getUser().isBot()) {
            Random random = new Random();
            int randomColor = random.nextInt(0xffffff + 1);
            EmbedBuilder bot = new EmbedBuilder();

            bot.setTitle(":robot: New Bot!");
            bot.setColor(randomColor);
            bot.setThumbnail(event.getMember().getUser().getAvatarUrl());
            bot.addField("New Bot's Name", event.getMember().getAsMention(), false);
            bot.setFooter("Quiver New Bot Welcome", Info.LOGO);

            event.getGuild().getDefaultChannel().sendMessage(bot.build()).queue();
        } else {
            Random random = new Random();
            int randomColor = random.nextInt(0xffffff + 1);
            EmbedBuilder user = new EmbedBuilder();

            user.setTitle("New Member!");
            user.setColor(randomColor);
            user.setThumbnail(event.getMember().getUser().getAvatarUrl());
            user.addField("New User's Name", event.getMember().getAsMention(),false);
            user.setFooter("Quiver New Member Welcome", Info.LOGO);

            event.getGuild().getDefaultChannel().sendMessage(user.build()).queue();

        }
    }

}