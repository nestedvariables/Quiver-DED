package com.nestedvariables.dev.Discord.Quiver.events.channelsystem;

import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.Info;
import com.nestedvariables.dev.Discord.Quiver.Prefix;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ChannelInvite extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(Prefix.getPrefix(event) + "privateinvite")) {
            if(args.length < 2) {
                EmbedBuilder nullUser = new EmbedBuilder();

                nullUser.setDescription(":white_medium_small_square: " + event.getMember().getAsMention()
                        + ", you didn't specify which member to invite!");
                nullUser.setColor(Info.ERROR_RED);
                nullUser.setFooter("Quiver Undefined Member", Info.LOGO);

                event.getChannel().sendMessage(nullUser.build()).queue((message) -> {
                    message.delete().queueAfter(10, TimeUnit.SECONDS);
                });
            }
        }
    }
}