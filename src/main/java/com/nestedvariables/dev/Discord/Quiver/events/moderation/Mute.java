package com.nestedvariables.dev.Discord.Quiver.events.moderation;

import java.util.List;
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

public class Mute extends ListenerAdapter {

    String muteReason = "";
    Role muteRole;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getChannel()) + "mute")) {
            List<Role> roles = event.getGuild().getRolesByName("Muted", true);

            if (roles.size() < 1) {
                muteRole = event.getGuild().getController().createRole().setName("Muted").setColor(0xffffff)
                        .setMentionable(false).complete();

                muteRole.getManager()
                        .revokePermissions(Permission.MESSAGE_TTS, Permission.MESSAGE_WRITE,
                                Permission.VOICE_DEAF_OTHERS, Permission.VOICE_MOVE_OTHERS,
                                Permission.VOICE_MUTE_OTHERS, Permission.VOICE_SPEAK, Permission.VOICE_USE_VAD,
                                Permission.NICKNAME_CHANGE, Permission.MESSAGE_ADD_REACTION)
                        .queue();

                event.getGuild().getOwner().getUser().openPrivateChannel().queue((channel) -> {
                    channel.sendMessage(
                            "Your discord server didn't have a mute role available and someone tried muting someone so I went ahead and created mute role for you with the following permissions")
                            .queue();
                    EmbedBuilder eb = new EmbedBuilder();

                    eb.setTitle("Default Muted Role Information");
                    eb.addField("Color", "White", false);
                    eb.addField("Denied Text Permissions:",
                            "`Add Reactions` \n`Change Nickname` \n`Create Instant Invite` \n`Message` \n`Message with Text to Speech`",
                            true);
                    eb.addField("Denied Voice Permissions:",
                            "`Deafen Others` \n`Move Others` \n`Mute Others` \n`Speak` \n`Use Voice Detection Activation`",
                            true);

                    channel.sendMessage(eb.build()).queue();
                    channel.sendMessage("Feel free to change the color or the permissions to your liking").queue();
                });

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

                        event.getGuild().getController().addSingleRoleToMember(memberToMute, muteRole).queue();

                        EmbedBuilder muteNoReason = new EmbedBuilder();

                        muteNoReason.setColor(randomColor);
                        muteNoReason.addField("Muted Member: ", memberToMute.getEffectiveName(), false);
                        muteNoReason.addField("Mute Executor: ", event.getMember().getEffectiveName(), false);
                        muteNoReason.addField("Reason: ", "Mute executor didn't specify a reason", false);
                        muteNoReason.setFooter("Quiver Mute Report", Info.LOGO);

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

                        event.getGuild().getController().addSingleRoleToMember(memberToMute, muteRole).queue();

                        EmbedBuilder muteWithReason = new EmbedBuilder();

                        muteWithReason.setColor(randomColor);
                        muteWithReason.addField("Muted Member: ", memberToMute.getEffectiveName(), false);
                        muteWithReason.addField("Mute Executor: ", event.getMember().getEffectiveName(), false);
                        muteWithReason.addField("Reason: ", muteReason, false);
                        muteWithReason.setFooter("Quiver Mute Report", Info.LOGO);

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
            } else {
                muteRole = event.getGuild().getRolesByName("Muted", true).get(0);
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

                        event.getGuild().getController().addSingleRoleToMember(memberToMute, muteRole).queue();

                        EmbedBuilder muteNoReason = new EmbedBuilder();

                        muteNoReason.setColor(randomColor);
                        muteNoReason.addField("Muted Member: ", memberToMute.getEffectiveName(), false);
                        muteNoReason.addField("Mute Executor: ", event.getMember().getEffectiveName(), false);
                        muteNoReason.addField("Reason: ", "Mute executor didn't specify a reason", false);
                        muteNoReason.setFooter("Quiver Mute Report", Info.LOGO);

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

                        event.getGuild().getController().addSingleRoleToMember(memberToMute, muteRole).queue();

                        EmbedBuilder muteWithReason = new EmbedBuilder();

                        muteWithReason.setColor(randomColor);
                        muteWithReason.addField("Muted Member: ", memberToMute.getEffectiveName(), false);
                        muteWithReason.addField("Mute Executor: ", event.getMember().getEffectiveName(), false);
                        muteWithReason.addField("Reason: ", muteReason, false);
                        muteWithReason.setFooter("Quiver Mute Report", Info.LOGO);

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
}