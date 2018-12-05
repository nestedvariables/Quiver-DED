package com.nestedvariables.dev.Discord.Quiver.events.channelsystem;

import java.util.EnumSet;
import java.util.List;

import com.nestedvariables.dev.Discord.Quiver.Bools;
import com.nestedvariables.dev.Discord.Quiver.Info;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ChannelCreate extends ListenerAdapter {

    Boolean nsfwBool;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(Info.PREFIX + "privatechannel")) {
            /*
             * if (Bools.isBlacklisted(event)) { event.getChannel().sendMessage(
             * event.getMember().getAsMention() +
             * " You can't use commands because you were blacklisted") .queue(); }
             */
            Integer slowmodeInt = Integer.parseInt(args[1]);

            if (args.toString().contains("-nsfw")) {
                nsfwBool = true;
            } else {
                nsfwBool = false;
            }

            EnumSet<Permission> pAllow = EnumSet.of(Permission.PRIORITY_SPEAKER, Permission.VOICE_USE_VAD,
                    Permission.VOICE_SPEAK, Permission.VOICE_MUTE_OTHERS, Permission.VOICE_DEAF_OTHERS,
                    Permission.VOICE_CONNECT, Permission.VIEW_CHANNEL, Permission.MESSAGE_ADD_REACTION);
            EnumSet<Permission> pTextAllow = EnumSet.of(Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_EXT_EMOJI,
                    Permission.MESSAGE_HISTORY, Permission.MESSAGE_MANAGE, Permission.MESSAGE_READ,
                    Permission.MESSAGE_WRITE);
            EnumSet<Permission> pDeny = EnumSet.of(Permission.BAN_MEMBERS, Permission.CREATE_INSTANT_INVITE,
                    Permission.KICK_MEMBERS, Permission.MANAGE_CHANNEL, Permission.MANAGE_EMOTES,
                    Permission.MANAGE_PERMISSIONS, Permission.MANAGE_ROLES, Permission.MANAGE_SERVER,
                    Permission.MANAGE_WEBHOOKS);
            List<Category> categoryPrivate = event.getGuild().getCategoriesByName("Private Channels", true);            
            if (categoryPrivate.size() < 1) {
                event.getGuild().getController().createCategory("Private Channels").queue();
                event.getGuild().getController()
                        .createTextChannel(event.getMember().getEffectiveName() + "'s-private-text-channel")
                        .queue((channel) -> {
                            channel.getManager().setParent(event.getGuild().getCategoriesByName("Private Channels", true).get(0));
                            channel.getManager().setTopic(event.getMember().getEffectiveName().toString()
                                    + "'s Private Text Channel \n\n Owner: " + event.getMember().getAsMention()
                                    + "\nInvited: None \n\n To invite users the owner of the channel must run the command "
                                    + Info.PREFIX + "privateinvite <mention a user>");
                            channel.getManager().setSlowmode(slowmodeInt);
                            channel.getManager().setNSFW(nsfwBool);
                            channel.getManager().putPermissionOverride(event.getMember(), pTextAllow, pDeny).queue();
                        });
                event.getGuild().getController()
                        .createVoiceChannel(event.getMember().getEffectiveName() + "'s Private Voice Channel")
                        .queue((channel) -> {
                            channel.getManager().setParent(event.getGuild().getCategoriesByName("Private Channels", true).get(0));
                            channel.getManager().setUserLimit(1);
                            channel.getManager().putPermissionOverride(event.getMember(), pAllow, pDeny).queue();
                            channel.getManager().putPermissionOverride(event.getGuild().getPublicRole(), null, null);
                        });
            } else {
                event.getGuild().getController()
                        .createTextChannel(event.getMember().getEffectiveName() + "'s-private-text-channel")
                        .queue((channel) -> {
                            channel.getManager().setParent(event.getGuild().getCategoriesByName("Private Channels", true).get(0));
                            channel.getManager().setTopic(event.getMember().getEffectiveName().toString()
                                    + "'s Private Text Channel \n\n Owner: " + event.getMember().getAsMention()
                                    + "\nInvited: None \n\n To invite users the owner of the channel must run the command "
                                    + Info.PREFIX + "privateinvite <mention a user>");
                            channel.getManager().setSlowmode(slowmodeInt);
                            channel.getManager().setNSFW(nsfwBool);
                            channel.getManager().putPermissionOverride(event.getMember(), pTextAllow, pDeny).queue();
                        });
                event.getGuild().getController()
                        .createVoiceChannel(event.getMember().getEffectiveName() + "'s Private Voice Channel")
                        .queue((channel) -> {
                            channel.getManager().setParent(event.getGuild().getCategoriesByName("Private Channels", true).get(0));
                            channel.getManager().setUserLimit(1);
                            channel.getManager().putPermissionOverride(event.getMember(), pAllow, pDeny).queue();
                            channel.getManager().putPermissionOverride(event.getGuild().getPublicRole(), null, null);
                        });
            }
        }
    }
}