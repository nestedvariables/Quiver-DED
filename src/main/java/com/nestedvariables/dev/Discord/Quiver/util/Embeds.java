package com.nestedvariables.dev.Discord.Quiver.util;

import java.util.Random;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;

public class Embeds {
    EmbedBuilder embed = new EmbedBuilder();

    public int color() {
        Random random = new Random();
        Integer randomColor = random.nextInt(0xffffff + 1);
        return randomColor;
    }
    
    // Bot Welcome Embed
    public EmbedBuilder joinWelcome(GuildJoinEvent event) {
        embed.setColor(color());
        embed.setDescription("Phew, I made it over the ~waves~ intact. Oh, hey there, I'm " + event.getJDA().getSelfUser().getAsMention() + " and I'd like to thank you for adding me to your Discord server, it feels pretty toasty in here. Here's some stuff you should know about me:");
        embed.addField("I'm known as", event.getJDA().getSelfUser().getAsMention(), true);
        embed.addField("My prefix is", data.getPrefix(event.getGuild()), true);
        event.getGuild().getDefaultChannel().sendMessage(embed.build()).queue();
        return embed;
    }

    // Member Join Embed
    public EmbedBuilder guildJoinMember() {

        return embed;
    }

    // Bot Join Embed
    public EmbedBuilder guildJoinBot() {

        return embed;
    }
}