package com.nestedvariables.dev.Discord.Quiver.events.guildjoinevents;

import java.util.Random;

import com.nestedvariables.dev.Discord.Quiver.Info;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class JoinAnnouncement extends ListenerAdapter {

    public void onGuildJoin(GuildJoinEvent event) {
        // Message saying thank you for inviting the bot and saying a message is about
        // to be sent with information on commands and how to run them
        event.getGuild().getDefaultChannel().sendMessage("Hi! I'm " + event.getJDA().getSelfUser().getAsMention()
                + ", and I'd like to thank you for welcoming me into your discord guild! \nHere is information about what I can do and how to do it:")
                .queue();

        // Random color generator for embed
        Random random = new Random();
        int randomColor = random.nextInt(0xffffff + 1);

        // Setup embed with command information
        EmbedBuilder eb1 = new EmbedBuilder();

        eb1.setTitle("Help Page 1");
        eb1.setColor(randomColor);
        eb1.setDescription(":white_medium_small_square: My name is: " + event.getJDA().getSelfUser().getAsMention()
                + "\n :white_medium_small_square: My prefix is: `" + Info.PREFIX + "`");

        // Send page 1 of command information embed
        event.getGuild().getDefaultChannel().sendMessage(eb1.build()).queue();
    }
}