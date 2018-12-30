package com.nestedvariables.dev.Discord.Quiver.events.moderation;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.Utils;
import com.nestedvariables.dev.Discord.Quiver.Info;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Unmute extends ListenerAdapter {

    String muteReason = "";
    
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getChannel()) + "unmute")) {

            Role muteRole = event.getGuild().getRolesByName("Muted", true).get(0);

            event.getMessage().delete().queue();
            if (event.getMember().hasPermission(Permission.MANAGE_ROLES)) {
                if (args.length < 2) {
                    EmbedBuilder nullUser = new EmbedBuilder();

                    nullUser.setDescription(":white_medium_small_square: " + event.getMember().getAsMention()
                            + ", you didn't specify which member to mute!");
                    nullUser.setColor(Info.ERROR_RED);
                    nullUser.setFooter("Quiver Undefined Member", Info.LOGO);

                    event.getChannel().sendMessage(nullUser.build()).queue((message) -> {
                        message.delete().queueAfter(10, TimeUnit.SECONDS);
                    });
                } else if (args.length < 3) {

                    Random random = new Random();
                    int randomColor = random.nextInt(0xffffff + 1);

                    Member memberToMute = event.getMessage().getMentionedMembers().get(0);

                    event.getGuild().getController().removeSingleRoleFromMember(memberToMute, muteRole).queue();

                    EmbedBuilder muteNoReason = new EmbedBuilder();

                    muteNoReason.setColor(randomColor);
                    muteNoReason.addField("Unmuted Member: ", memberToMute.getEffectiveName(), false);
                    muteNoReason.addField("Unmute Executor: ", event.getMember().getEffectiveName(), false);
                    muteNoReason.addField("Reason: ", "Unmute executor didn't specify a reason", false);
                    muteNoReason.setFooter("Quiver Unmute Report", Info.LOGO);

                    event.getChannel().sendMessage(muteNoReason.build()).queue((message) -> {
                        message.delete().queueAfter(15, TimeUnit.SECONDS);
                    });

                } else {
                    Random random = new Random();
                    int randomColor = random.nextInt(0xffffff + 1);

                    Member memberToMute = event.getMessage().getMentionedMembers().get(0);

                    for (int i = 2; i < args.length; i++) {
                        muteReason = muteReason + args[i] + " ";
                    }

                    event.getGuild().getController().removeSingleRoleFromMember(memberToMute, muteRole).queue();

                    EmbedBuilder muteWithReason = new EmbedBuilder();

                    muteWithReason.setColor(randomColor);
                    muteWithReason.addField("Unmuted Member: ", memberToMute.getEffectiveName(), false);
                    muteWithReason.addField("Unmute Executor: ", event.getMember().getEffectiveName(), false);
                    muteWithReason.addField("Reason: ", muteReason, false);
                    muteWithReason.setFooter("Quiver Unmute Report", Info.LOGO);

                    event.getChannel().sendMessage(muteWithReason.build()).queue((message) -> {
                        message.delete().queueAfter(15, TimeUnit.SECONDS);
                    });

                    muteReason = "";
                }
            } else {
                EmbedBuilder nullPerms = new EmbedBuilder();

                nullPerms.setDescription(":white_medium_small_square: " + event.getMember().getAsMention()
                        + ", you don't have sufficient permissions. \n :white_medium_small_square: You require the permission to manage member's roles for this guild to use this command.");
                nullPerms.setColor(Info.ERROR_RED);
                nullPerms.setFooter("Quiver Insufficient Permissions", Info.LOGO);

                event.getChannel().sendMessage(nullPerms.build()).queue((message) -> {
                    message.delete().queueAfter(10, TimeUnit.SECONDS);
                });
            }
        }
    }

}