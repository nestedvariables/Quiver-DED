package com.nestedvariables.dev.Discord.Quiver.events.owner;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.Bools;
import com.nestedvariables.dev.Discord.Quiver.GuildData;
import com.nestedvariables.dev.Discord.Quiver.Info;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Announcement extends ListenerAdapter {

    String announcement = "";
 
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(GuildData.getPrefix(event.getGuild()) + "announce") || args[0].equalsIgnoreCase(GuildData.getPrefix(event.getGuild()) + "ann")) {
            if(Bools.isBotOwner(event)) {
                if(args.length < 2){
                    EmbedBuilder nullReason = new EmbedBuilder();

                    nullReason.setDescription("You didn't specify a reason for the announcement");
                    nullReason.setColor(Info.ERROR_RED);
                    nullReason.setFooter("Quiver Announcement Failure", Info.LOGO);

                    event.getChannel().sendMessage(nullReason.build()).queue((message) -> {
                        message.delete().queueAfter(15, TimeUnit.SECONDS);
                    });
                }else{
                    Random random = new Random();
                    int randomColor = random.nextInt(0xffffff + 1);

                    for (int i = 1; i < args.length; i++) {
                        announcement = announcement + args[i] + " ";
                    }

                    EmbedBuilder announce = new EmbedBuilder();
                    announce.setTitle("New Announcement!");
                    announce.setColor(randomColor);
                    announce.setDescription(announcement);
                    announce.setFooter("Quiver Announcement", Info.LOGO);

                    List<Guild> guilds = event.getJDA().getGuilds();
                    for (int guildCount = 0; guildCount < guilds.size(); guildCount++){
                        String guildID = guilds.get(guildCount).getId().toString();
                        event.getJDA().getGuildById(guildID).getOwner().getUser().openPrivateChannel().queue((channel)-> {
                            channel.sendMessage(announce.build()).queue();
                        });
                    }
                    announcement = "";
                }
                
            }else {
                EmbedBuilder nullUser = new EmbedBuilder();

                nullUser.setDescription("You can't use that command as you are not my owner: ExZiByte");
                nullUser.setColor(Info.ERROR_RED);
                nullUser.setFooter("Quiver Announcement Invalid User", Info.LOGO);

                event.getChannel().sendMessage(nullUser.build()).queue((message) -> {
                    message.delete().queueAfter(15, TimeUnit.SECONDS);
                });
            }
        }
    }
} 