package com.nestedvariables.dev.Discord.Quiver.events.owner;

import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.Checks;
import com.nestedvariables.dev.Discord.Quiver.Utils;
import com.nestedvariables.dev.Discord.Quiver.Info;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ErrorTest extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        /*
        if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getChannel()) + "no u  bad")) {
            if (Checks.isBotOwner(event)) {
                EmbedBuilder error = new EmbedBuilder();

                error.setDescription("An internal server error occured on our end. Please join our [Support Server](https://discord.gg/p9xj9UD \"support Discord server\"). Please take a screenshot of this error and the command that you tried to use and upload it in " + event.getJDA().getGuildById("488137783127572491").getTextChannelById("527357059654483968").getName());
                error.setColor(Info.ERROR_RED);
                error.setFooter("Quiver System Error", Info.LOGO);

                event.getChannel().sendMessage(error.build()).queue((message) -> {
                    message.delete().queueAfter(20, TimeUnit.SECONDS);
                });
            }

        }*/
    }
}