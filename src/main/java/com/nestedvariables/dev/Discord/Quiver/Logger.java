package com.nestedvariables.dev.Discord.Quiver;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;

public class Logger {

    // Logger method
    public static void log(String type, String info, Guild guild, Channel channel) {
        if (guild == null || channel == null) {

        } 
        else {
            switch (type) {
                case "warning":
                    System.out.println(info);
                case "fatal":
                    EmbedBuilder fatal = new EmbedBuilder();
                    fatal.setTitle(Utils.getMessage(channel, "fatalErrorEmbedTitle"));
                    guild.getTextChannelById(channel.getId()).sendMessage(fatal.build()).queue();
                    fatal.clear();
                    System.out.println(info);
            }
        }
    }
}