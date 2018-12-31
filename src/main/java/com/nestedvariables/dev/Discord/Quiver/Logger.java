package com.nestedvariables.dev.Discord.Quiver;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;

public class Logger {

    // Logger method
    public static void log(String type, String info, Guild guild) {
        switch (type) {
            case "warning":
                System.out.println(info);
            case "fatal":
                EmbedBuilder fatal = new EmbedBuilder();
                fatal.setTitle(Utils.getMessage(guild, "fatalErrorEmbedTitle"));
                fatal.setDescription(Utils.getMessage(guild, "fatalErrorEmbedDescription").replace("{error}", "```\n" + info + "\n```"));
                fatal.setFooter(Utils.getMessage(guild, "name") + " " + Utils.getMessage(guild, "fatalErrorEmbedFooter"), guild.getJDA().getSelfUser().getAvatarUrl());
                fatal.setColor(0xff5555);
                if (Utils.getLogChannel(guild) == null) {
                    guild.getDefaultChannel().sendMessage(fatal.build()).queue();
                }
                else {
                    guild.getTextChannelById(Utils.getLogChannel(guild)).sendMessage(fatal.build()).queue();
                }
                fatal.clear();
                System.out.println(info);
            }
    }
}