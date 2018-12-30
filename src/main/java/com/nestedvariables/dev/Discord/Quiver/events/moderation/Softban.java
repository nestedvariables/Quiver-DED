package com.nestedvariables.dev.Discord.Quiver.events.moderation;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.Utils;
import com.nestedvariables.dev.Discord.Quiver.Info;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Invite;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Softban extends ListenerAdapter {

    String banReason = " ";

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(Utils.getPrefix(event.getChannel()) + "softban")){
            Invite invite = event.getGuild().getDefaultChannel().createInvite().setMaxAge(0).setMaxUses(1).setUnique(true).complete();
            event.getMessage().delete().queue();
            if (event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
                if (args.length < 2) {
                    EmbedBuilder nullUser = new EmbedBuilder();

                    nullUser.setDescription(":white_medium_small_square: " + event.getMember().getAsMention()
                            + ", you didn't specify which member to softban!");
                    nullUser.setColor(Info.ERROR_RED);
                    nullUser.setFooter("Quiver Undefined Member", Info.LOGO);

                    event.getChannel().sendMessage(nullUser.build()).queue((message) -> {
                        message.delete().queueAfter(10, TimeUnit.SECONDS);
                    });
                }else if(args.length < 3) {
                    
                    Member memberToBan = event.getMessage().getMentionedMembers().get(0);
                    EmbedBuilder nullReason = new EmbedBuilder();

                    nullReason.setDescription(":white_medium_small_square: " + event.getMember().getAsMention()
                    + ", you didn't specify a reason for softbanning " + memberToBan.getAsMention() + "!");
                    nullReason.setColor(Info.ERROR_RED);
                    nullReason.setFooter("Quiver Undefined Reason" , Info.LOGO);

                    event.getChannel().sendMessage(nullReason.build()).queue((message) -> {
                        message.delete().queueAfter(10,TimeUnit.SECONDS);
                    });

                } else {
                    Random random = new Random();
                    int randomColor = random.nextInt(0xffffff + 1);

                    Member memberToBan = event.getMessage().getMentionedMembers().get(0);

                    for (int i = 2; i < args.length; i++) {
                       banReason = banReason + args[i] + " ";
                    }

                    event.getGuild().getMemberById(memberToBan.getUser().getId()).getUser().openPrivateChannel().queue((channel)-> {
                        channel.sendMessage(invite.toString().replace("Invite(","https://discord.gg/").replace(")"," ")).queue();
                        
                    });

                    event.getGuild().getController().ban(memberToBan, 7, banReason).queue();
                    event.getGuild().getController().unban(memberToBan.getUser()).queue();

                    EmbedBuilder ban = new EmbedBuilder();

                    ban.setColor(randomColor);
                    ban.addField("SoftBanned Member: ", memberToBan.getEffectiveName() ,false);
                    ban.addField("SoftBan Executor: ", event.getMember().getEffectiveName(), false);
                    ban.addField("Reason: " , banReason , false);
                    ban.setFooter("Quiver SoftBan Report", Info.LOGO);

                    event.getChannel().sendMessage(ban.build()).queue((message) -> {
                        message.delete().queueAfter(15, TimeUnit.SECONDS);
                    });
                    
                    banReason = "";
                }
            } else {
                EmbedBuilder nullPerms = new EmbedBuilder();

                nullPerms.setDescription(":white_medium_small_square: " + event.getMember().getAsMention()
                        + ", you don't have sufficient permissions. \n :white_medium_small_square: You require the permission to ban members from this guild to use this command.");
                nullPerms.setColor(Info.ERROR_RED);
                nullPerms.setFooter("Quiver Insufficient Permissions", Info.LOGO);

                event.getChannel().sendMessage(nullPerms.build()).queue((message) -> {
                    message.delete().queueAfter(10, TimeUnit.SECONDS);
                });
            }
        }
    }
    
}