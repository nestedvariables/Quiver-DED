package com.nestedvariables.dev.Discord.Quiver.events.channelsystem;

import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.Checks;
import com.nestedvariables.dev.Discord.Quiver.Utils;
import com.nestedvariables.dev.Discord.Quiver.Info;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ChannelCreate extends ListenerAdapter {

        Boolean nsfwBool;

        public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
                String[] args = event.getMessage().getContentRaw().split("\\s+");
                if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + "privatechannel")) {
                        if (Checks.isBlacklisted(event)) {
                                event.getChannel().sendMessage(event.getMember().getAsMention() + " You can't use commands because you were blacklisted").queue();
                        } else {
                                if (Checks.isChannelSystemEnabled(event)) {
                                        if(args.length < 2) {
                                                EmbedBuilder nullArgs = new EmbedBuilder();

                                                nullArgs.setTitle("Invalid Usage");
                                                nullArgs.setColor(Info.ERROR_RED);
                                                nullArgs.setDescription("Correct Usage: `" + Utils.getPrefix(event.getGuild()) + "privatechannel <slowmode time> {-nsfw}` \nKey:\n<> | Required\n{} | Optional");
                                                nullArgs.setFooter("Quiver Invalid Usage", Info.LOGO);

                                                event.getChannel().sendMessage(nullArgs.build()).queue((message) -> {
                                                        message.delete().queueAfter(10, TimeUnit.SECONDS);
                                                });

                                        } else {

                                        Integer slowmodeInt = Integer.parseInt(args[1]);

                                        if (args.toString().contains("-nsfw")) {
                                                nsfwBool = true;
                                        } else {
                                                nsfwBool = false;
                                        }

                                        EnumSet<Permission> pAllow = EnumSet.of(Permission.PRIORITY_SPEAKER, Permission.VOICE_USE_VAD, Permission.VOICE_SPEAK, Permission.VOICE_MUTE_OTHERS, Permission.VOICE_DEAF_OTHERS, Permission.VOICE_CONNECT, Permission.VIEW_CHANNEL, Permission.MESSAGE_ADD_REACTION);
                                        EnumSet<Permission> pTextAllow = EnumSet.of(Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_EXT_EMOJI, Permission.MESSAGE_HISTORY, Permission.MESSAGE_MANAGE, Permission.MESSAGE_READ, Permission.MESSAGE_WRITE);
                                        EnumSet<Permission> pDeny = EnumSet.of(Permission.BAN_MEMBERS, Permission.CREATE_INSTANT_INVITE, Permission.KICK_MEMBERS, Permission.MANAGE_CHANNEL, Permission.MANAGE_EMOTES, Permission.MANAGE_PERMISSIONS, Permission.MANAGE_ROLES, Permission.MANAGE_SERVER, Permission.MANAGE_WEBHOOKS);
                                        EnumSet<Permission> pTextDenyEveryone = EnumSet.of(Permission.MESSAGE_ADD_REACTION, Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_EXT_EMOJI, Permission.MESSAGE_HISTORY, Permission.MESSAGE_MANAGE, Permission.MESSAGE_MENTION_EVERYONE, Permission.MESSAGE_READ, Permission.MESSAGE_TTS, Permission.MESSAGE_WRITE);
                                        EnumSet<Permission> pVoiceDenyEveryone = EnumSet.of(Permission.PRIORITY_SPEAKER, Permission.VOICE_CONNECT);
                                        List<Category> categoryPrivate = event.getGuild().getCategoriesByName("Private Channels", true);
                                        if (categoryPrivate.size() < 1) {
                                                event.getGuild().getController().createCategory("Private Channels").queue();
                                                event.getGuild().getController().createTextChannel(event.getMember().getEffectiveName() + "s-private-text-channel").queue((channel) -> {
                                                                        channel.getManager().setParent(event.getGuild().getCategoriesByName("Private Channels", true).get(0));
                                                                        channel.getManager().setTopic(event.getMember().getEffectiveName().toString() + "'s Private Text Channel \n\nOwner: " + event.getMember().getAsMention() + "\nInvited: None \n\nTo invite users the owner of the channel must run the command " + Utils.getPrefix(event.getGuild())  + "privateinvite <mention a user>");
                                                                        channel.getManager().setSlowmode(slowmodeInt); 
                                                                        channel.getManager().setNSFW(nsfwBool);
                                                                        channel.getManager().putPermissionOverride(event.getMember(), pTextAllow, pDeny).queue();
                                                                        channel.getManager().putPermissionOverride(event.getGuild().getPublicRole(), null, pTextDenyEveryone).queue();
                                                                });
                                                event.getGuild().getController().createVoiceChannel(event.getMember().getEffectiveName() + "'s Private Voice Channel").queue((channel) -> {
                                                                        channel.getManager().setParent(event.getGuild().getCategoriesByName("Private Channels", true).get(0));
                                                                        channel.getManager().setUserLimit(1);
                                                                        channel.getManager().putPermissionOverride(event.getMember(), pAllow, pDeny).queue();
                                                                        channel.getManager().putPermissionOverride(event.getGuild().getPublicRole(), null, pVoiceDenyEveryone).queue();
                                                                });
                                        } else {
                                                event.getGuild().getController().createTextChannel(event.getMember().getEffectiveName() + "'s-private-text-channel").queue((channel) -> {
                                                                        channel.getManager().setParent(event.getGuild().getCategoriesByName("Private Channels", true).get(0));
                                                                        channel.getManager().setTopic(event.getMember().getEffectiveName().toString() + "'s Private Text Channel \n\nOwner: " + event.getMember().getAsMention() + "\nInvited: None \n\nTo invite users the owner of the channel must run the command " + Utils.getPrefix(event.getGuild()) + "privateinvite <mention a user>");
                                                                        channel.getManager().setSlowmode(slowmodeInt);
                                                                        channel.getManager().setNSFW(nsfwBool);
                                                                        channel.getManager().putPermissionOverride(event.getMember(), pTextAllow, pDeny).queue();
                                                                        channel.getManager().putPermissionOverride(event.getGuild().getPublicRole(), null, pTextDenyEveryone);
                                                                });
                                                event.getGuild().getController().createVoiceChannel(event.getMember().getEffectiveName() + "'s Private Voice Channel").queue((channel) -> {
                                                                        channel.getManager().setParent(event.getGuild().getCategoriesByName("Private Channels", true).get(0));
                                                                        channel.getManager().setUserLimit(1);
                                                                        channel.getManager().putPermissionOverride(event.getMember(), pAllow, pDeny).queue();
                                                                        channel.getManager().putPermissionOverride(event.getGuild().getPublicRole(), null, pVoiceDenyEveryone);
                                                                });
                                        }
                                }
                                } else {
                                        EmbedBuilder disSys = new EmbedBuilder();
                                        
                                        disSys.setColor(Info.ERROR_RED);
                                        disSys.setDescription(event.getMember().getAsMention() + " I can't create a private channel as " + event.getGuild().getOwner().getAsMention() + " has disabled the channel system.");
                                        disSys.setFooter("Quiver Disabled System Error", Info.LOGO);

                                        event.getChannel().sendMessage(disSys.build()).queue((message) -> {
                                                message.delete().queueAfter(15, TimeUnit.SECONDS);
                                        });
                                }
                        }
                }
        }
}