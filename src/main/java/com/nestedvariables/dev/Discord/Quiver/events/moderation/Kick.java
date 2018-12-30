package com.nestedvariables.dev.Discord.Quiver.events.moderation;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.Utils;
import com.nestedvariables.dev.Discord.Quiver.Info;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Kick extends ListenerAdapter {

    String kickReason = "";

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + "kick")) {
            if (event.getMember().hasPermission(Permission.KICK_MEMBERS)) {
                if (args.length < 2) {
                    EmbedBuilder nullUser = new EmbedBuilder();

                    nullUser.setDescription(":white_medium_small_square: " + event.getMember().getAsMention()
                            + ", you didn't specify which member to kick!");
                    nullUser.setColor(Info.ERROR_RED);
                    nullUser.setFooter("Quiver Undefined Member", Info.LOGO);

                    event.getChannel().sendMessage(nullUser.build()).queue((message) -> {
                        message.delete().queueAfter(10, TimeUnit.SECONDS);
                    });

                } else if (args.length < 3) {
                    Member memberToKick = event.getMessage().getMentionedMembers().get(0);
                    EmbedBuilder nullReason = new EmbedBuilder();

                    nullReason.setDescription(":white_medium_small_square: " + event.getMember().getAsMention()
                            + ", you didn't specify a reason for kicking " + memberToKick.getAsMention() + "!");
                } else {
                    Random random = new Random();
                    int randomColor = random.nextInt(0xffffff + 1);

                    Member memberToKick = event.getMessage().getMentionedMembers().get(0);

                    for (int i = 2; i < args.length; i++) {
                        kickReason = kickReason + args[i] + " ";
                    }

                    event.getGuild().getController().kick(memberToKick, kickReason).queue();

                    EmbedBuilder kick = new EmbedBuilder();

                    kick.setColor(randomColor);
                    kick.addField("Kicked Member: ", memberToKick.getEffectiveName(), false);
                    kick.addField("Kick Executor: ", event.getMember().getEffectiveName(), false);
                    kick.addField("Reason: ", kickReason, false);
                    kick.setFooter("Quiver Kick Report", Info.LOGO);

                    event.getChannel().sendMessage(kick.build()).queue((message) -> {
                        message.delete().queueAfter(15, TimeUnit.SECONDS);
                    });
                    kickReason = "";                    
                }

            }else {
                EmbedBuilder nullPerms = new EmbedBuilder();
    
                nullPerms.setDescription(":white_medium_small_square: " + event.getMember().getAsMention()
                        + ", you don't have sufficient permissions. \n :white_medium_small_square: You require the permission to kick members from this guild to use this command.");
                nullPerms.setColor(Info.ERROR_RED);
                nullPerms.setFooter("Quiver Insufficient Permissions", Info.LOGO);
    
                event.getChannel().sendMessage(nullPerms.build()).queue((message) -> {
                    message.delete().queueAfter(10, TimeUnit.SECONDS);
                });
            }

        } 
    }

}