package com.nestedvariables.dev.Discord.Quiver.events.music;

import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.Info;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Play extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");
        
        if(args[0].equalsIgnoreCase(Info.PREFIX + "play")) {
            if(event.getMember().getVoiceState().inVoiceChannel() == false) {
                EmbedBuilder noChannel = new EmbedBuilder();

                noChannel.setColor(Info.ERROR_RED);
                noChannel.setDescription(event.getAuthor().getAsMention() + ", you need to join a voice channel before using this command!");
                noChannel.setFooter("Quiver User Invalid Voice State", Info.LOGO);

                event.getChannel().sendMessage(noChannel.build()).queue((message) -> {
                    message.delete().queueAfter(10, TimeUnit.SECONDS);
                });
            }
            else{

            if(args.length < 2){
                EmbedBuilder nullArgs1 = new EmbedBuilder();

                nullArgs1.setDescription("");
                nullArgs1.setColor(Info.ERROR_RED);
                nullArgs1.setFooter("Quiver Not Enough Arguments", Info.LOGO);

                event.getChannel().sendMessage(nullArgs1.build()).queue((message) -> {
                    message.delete().queueAfter(10, TimeUnit.SECONDS);
                });
            }
        }

        }
        

    }

}