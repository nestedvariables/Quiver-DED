package com.nestedvariables.dev.Discord.Quiver.events.moderation;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.Info;
import com.nestedvariables.dev.Discord.Quiver.Prefix;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Clear extends ListenerAdapter {

    Integer messageDelete;
    String messageDeleteS;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase(Prefix.getPrefix(event) + "clear") || args[0].equalsIgnoreCase(Prefix.getPrefix(event) + "c")) {
            event.getMessage().delete().queue();
            if (event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                
                if (args.length < 2) {
                    EmbedBuilder nullArgs1 = new EmbedBuilder();

                    nullArgs1.setDescription(":white_medium_small_square: " + event.getMember().getAsMention()
                            + ", you didn't specify the amount of messages to delete");
                    nullArgs1.setColor(Info.ERROR_RED);
                    nullArgs1.setFooter("Quiver Undefined Message Count", Info.LOGO);

                    event.getChannel().sendMessage(nullArgs1.build()).queue((message) -> {
                        message.delete().queueAfter(10, TimeUnit.SECONDS);
                    });
                } else {

                    messageDeleteS = args[1];
                    messageDelete = Integer.parseInt(messageDeleteS);

                    if (messageDelete < 1) {
                        EmbedBuilder notEnoughMessages = new EmbedBuilder();

                        notEnoughMessages.setDescription("");
                    }

                    if (messageDelete > 101) {

                        EmbedBuilder tooManyMessages = new EmbedBuilder();

                        tooManyMessages.setDescription(":white_medium_small_square: " + event.getMember().getAsMention()
                                + ", the amount of messages you specified to delete were too many to delete at once. " 
                                + "\n:white_medium_small_square: Or the messages attempting to be deleted are older the 2 weeks." 
                                + " \n :white_medium_small_square: Maximum amount of messages you can delete at a time is 100.");
                        tooManyMessages.setColor(Info.ERROR_RED);
                        tooManyMessages.setFooter("Quiver Message Delete", Info.LOGO);

                        event.getChannel().sendMessage(tooManyMessages.build()).queue((message) -> {
                            message.delete().queueAfter(15, TimeUnit.SECONDS);
                        });
                        
                    } else {
                        Random random = new Random();
                        int randomColor = random.nextInt(0xffffff + 1);

                        List<Message> msgs = event.getChannel().getHistory().retrievePast(messageDelete).complete();
                        event.getChannel().deleteMessages(msgs).queue();

                        EmbedBuilder success = new EmbedBuilder();

                        success.setDescription(":white_medium_small_square: Successfully deleted " + messageDelete
                                + " messages from " + event.getChannel().getAsMention());
                        success.setColor(randomColor);
                        success.setFooter("Quiver Message Clear", Info.LOGO);  
                        
                        event.getChannel().sendMessage(success.build()).queue((message) -> {
                            message.delete().queueAfter(5,TimeUnit.SECONDS);
                        });
                    }
                }
            } else {
                EmbedBuilder nullPerms = new EmbedBuilder();

                nullPerms.setDescription(":white_medium_small_square: " + event.getMember().getAsMention()
                        + ", you don't have sufficient permissions. \n :white_medium_small_square: You require the permission to manage messages for this guild to use this command.");
                nullPerms.setColor(Info.ERROR_RED);
                nullPerms.setFooter("Quiver Insufficient Permissions", Info.LOGO);

                event.getChannel().sendMessage(nullPerms.build()).queue((message) -> {
                    message.delete().queueAfter(10, TimeUnit.SECONDS);
                });
            }
        }
    }

}