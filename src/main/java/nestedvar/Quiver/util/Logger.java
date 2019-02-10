package nestedvar.Quiver.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;

public class Logger {

    // Log the error (with guild provided)
    public void log(int code, String error, Guild guild) {
        EmbedBuilder fatal = new EmbedBuilder();
        fatal.setDescription(error);
        guild.getTextChannelById("491459187620970505").sendMessage(fatal.build()).queue();
    }

    // Log the error (no guild provided)
    public void log(int code, String error) {
        System.out.println(error);
    }


        // TODO port code
        /*if (guild != null) {
            switch (type) {
            case "warning":
                EmbedBuilder warning = new EmbedBuilder();
                warning.setTitle(Utils.loggerMessages.get("warningEmbedTitle"));
                warning.setDescription(Utils.loggerMessages.get("warningEmbedDescription"));
                warning.setFooter(Utils.loggerMessages.get("warningEmbedFooter"), guild.getJDA().getSelfUser().getAvatarUrl());
                warning.setColor(Utils.embedColor("warning"));
                if (Utils.getLogChannel(guild) == null) {
                    guild.getDefaultChannel().sendMessage(warning.build()).queue();
                } else {
                    guild.getTextChannelById(Utils.getLogChannel(guild)).sendMessage(warning.build()).queue();
                }
                warning.clear();
                System.out.println(info);
            case "fatal":
                EmbedBuilder fatal = new EmbedBuilder();
                fatal.setTitle(Utils.loggerMessages.get("fatalErrorEmbedTitle"));
                fatal.setDescription(Utils.loggerMessages.get("fatalErrorEmbedDescription").replace("{error}", "```\n" + info + "\n```"));
                fatal.setFooter(Utils.loggerMessages.get("fatalErrorEmbedFooter"), guild.getJDA().getSelfUser().getAvatarUrl());
                fatal.setColor(Utils.embedColor("error"));
                if (Utils.getLogChannel(guild) == null) {
                    guild.getDefaultChannel().sendMessage(fatal.build()).queue();
                } else {
                    guild.getTextChannelById(Utils.getLogChannel(guild)).sendMessage(fatal.build()).queue();
                }
                fatal.clear();
                System.out.println(info);
            }
        } else {
            switch (type) {
            case "warning":
                EmbedBuilder warning = new EmbedBuilder();
                warning.setTitle(Utils.loggerMessages.get("warningEmbedTitle"));
                warning.setDescription(Utils.loggerMessages.get("warningEmbedDescription"));
                warning.setFooter(Utils.loggerMessages.get("warningEmbedFooter"), Quiver.shardManager.getApplicationInfo().getJDA().getSelfUser().getAvatarUrl());
                warning.setColor(Utils.embedColor("warning"));
                Quiver.shardManager.getGuildById("488137783127572491").getTextChannelById("517756124846358529").sendMessage(warning.build()).queue();
                warning.clear();
                System.out.println(info);
                
            case "fatal":
                EmbedBuilder fatal = new EmbedBuilder();
                fatal.setTitle(Utils.loggerMessages.get("fatalErrorEmbedTitle"));
                fatal.setDescription(Utils.loggerMessages.get("fatalErrorEmbedDescription").replace("{error}", "```\n" + info + "\n```"));
                fatal.setFooter(Utils.loggerMessages.get("fatalErrorEmbedFooter"),Quiver.shardManager.getApplicationInfo().getJDA().getSelfUser().getAvatarUrl());
                fatal.setColor(Utils.embedColor("error"));
                Quiver.shardManager.getGuildById("488137783127572491").getTextChannelById("517756124846358529").sendMessage(fatal.build()).queue();
                fatal.clear();
                System.out.println(info);
            }
        }*/
}