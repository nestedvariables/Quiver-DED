package com.nestedvariables.dev.Discord.Quiver.events.information;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.Utils;
import com.nestedvariables.dev.Discord.Quiver.Info;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Help extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + "help")) {
            Random random = new Random();
            int randomColor = random.nextInt(0xffffff + 1);
            if (Utils.isBlacklisted(event)) {
                event.getChannel().sendMessage(event.getMember().getAsMention() + " You can't use commands because you were blacklisted").queue();
            } else if (Utils.isBotOwner(event)) {
                EmbedBuilder owner = new EmbedBuilder();

                owner.setTitle(":tools: Owner Help");
                owner.setColor(randomColor);
                owner.addField(Utils.getPrefix(event.getGuild()) + Utils.getMessage(event.getGuild(), "announce"), "Will send a message to the owner of every server Quiver is in.", false);
                owner.addField(Utils.getPrefix(event.getGuild()) + Utils.getMessage(event.getGuild(), "blacklist"), "Will blacklist a user from using Quiver", false);
                owner.addField(Utils.getPrefix(event.getGuild()) + "botinfo", "Will display all information on the bot. \nOptionally use: `--dm` to send in DM.", false);
                owner.addField(Utils.getPrefix(event.getGuild()) + "help", "Will show this embed. \nOptionally use: `--dm` to send in DM.", false);
                owner.addField(Utils.getPrefix(event.getGuild()) + "serverinfo", "Will send an embed with information about the server the command is used in. \nOptionally use: `--dm` to send in DM", false);
                owner.addField(Utils.getPrefix(event.getGuild()) + "userinfo", "Will send an embed with information about the user that ran the command \nOptionally use: `--dm` to send in DM", false);
                owner.setFooter("Quiver's Owner Help", Info.LOGO);

                if (args.length < 2) {
                    event.getChannel().sendMessage(owner.build()).queue();
                } else if (args.length == 2 && args[1].equalsIgnoreCase("--dm")) {
                    event.getAuthor().openPrivateChannel().queue((channel) -> {
                        channel.sendMessage(owner.build()).queue((message) -> {
                            message.delete().queueAfter(60, TimeUnit.SECONDS);
                        });
                    });
                }

            } else if (Utils.isServerOwner(event)) {

                EmbedBuilder serverOwner = new EmbedBuilder();

                if (args.length < 2) {
                    event.getChannel().sendMessage(serverOwner.build()).queue();
                } else if (args.length == 2 && args[1].equalsIgnoreCase("--dm")) {
                    event.getAuthor().openPrivateChannel().queue((channel) -> {
                        channel.sendMessage(serverOwner.build()).queue((message) -> {
                            message.delete().queueAfter(60, TimeUnit.SECONDS);
                        });
                    });
                }

            } else if (event.getMember().hasPermission(Permission.ADMINISTRATOR)){

            } else if (event.getMember().hasPermission(Permission.MANAGE_ROLES)) {

            } else {
                EmbedBuilder regular = new EmbedBuilder();

                regular.setTitle("Default Help");
                regular.setColor(randomColor);
                regular.addField(Utils.getPrefix(event.getGuild()) + Utils.getMessage(event.getGuild(), "botinfo"), "Will display information on the bot. \nOptionally use: `--dm` to send in DM", false);
                regular.addField(Utils.getPrefix(event.getGuild()) + Utils.getMessage(event.getGuild(), "help"), "Will display this. \nOptionally use `--dm` to send in DM", false);
                regular.addField(Utils.getPrefix(event.getGuild()) + Utils.getMessage(event.getGuild(), "serverinfo"), "Will display information on the server the command is used in.", false);
                regular.addField(Utils.getPrefix(event.getGuild()) + Utils.getMessage(event.getGuild(), "userinfo"), "Will display information about the user that ran the command \nOptionally tag a member of the server to get information on them", false);
                regular.setFooter("Quiver Default User Help", Info.LOGO);

                event.getChannel().sendMessage(regular.build()).queue((message) -> {
                    message.delete().queueAfter(30, TimeUnit.SECONDS);
                });
            }

        }
    }
}
