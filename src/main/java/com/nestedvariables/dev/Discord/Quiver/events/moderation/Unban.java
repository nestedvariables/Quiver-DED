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

public class Unban extends ListenerAdapter {

    String banReason = "";

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + "unban")){
            if(event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
                if(args.length < 2){
                    EmbedBuilder nullUser = new EmbedBuilder();

                    nullUser.setDescription(":white_medium_small_square: " + event.getMember().getAsMention()
                            + ", you didn't specify which user to unban!");
                    nullUser.setColor(Info.ERROR_RED);
                    nullUser.setFooter("Quiver Undefined User", Info.LOGO);

                    event.getChannel().sendMessage(nullUser.build()).queue((message) -> {
                        message.delete().queueAfter(10, TimeUnit.SECONDS);
                    });
                } else if(args.length < 3) {
                    Random random = new Random();
                    int randomColor = random.nextInt(0xffffff + 1);
                    
                    String memberToBan = args[1];
                   
                    event.getGuild().getController().unban(memberToBan).queue();

                    EmbedBuilder banNoReason = new EmbedBuilder();

                    banNoReason.setColor(randomColor);
                    banNoReason.addField("Unbanned Member: ", memberToBan ,false);
                    banNoReason.addField("Unban Executor: ", event.getMember().getEffectiveName(), false);
                    banNoReason.addField("Reason: " , "Unban executor didn't specify a reason" , false);
                    banNoReason.setFooter("Quiver Unban Report", Info.LOGO);

                    event.getChannel().sendMessage(banNoReason.build()).queue((message) -> {
                        message.delete().queueAfter(15, TimeUnit.SECONDS);
                    });
                } else {
                    Random random = new Random();
                    int randomColor = random.nextInt(0xffffff + 1);
                    
                    Member memberToBan = event.getMessage().getMentionedMembers().get(0);
                                        
                    for (int i = 2; i < args.length; i++) {
                       banReason = banReason + args[i] + " " ;
                    }

                    event.getGuild().getController().unban(memberToBan.getUser()).queue();

                    EmbedBuilder banWithReason = new EmbedBuilder();

                    banWithReason.setColor(randomColor);
                    banWithReason.addField("Unbanned Member: ", memberToBan.getEffectiveName() ,false);
                    banWithReason.addField("Unban Executor: ", event.getMember().getEffectiveName(), false);
                    banWithReason.addField("Reason: " , banReason , false);
                    banWithReason.setFooter("Quiver Unban Report", Info.LOGO);

                    event.getChannel().sendMessage(banWithReason.build()).queue((message) -> {
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