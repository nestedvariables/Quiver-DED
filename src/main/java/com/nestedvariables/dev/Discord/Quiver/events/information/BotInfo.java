package com.nestedvariables.dev.Discord.Quiver.events.information;

import java.util.Random;

import com.nestedvariables.dev.Discord.Quiver.Info;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotInfo extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        
        if (args[0].equalsIgnoreCase(Info.PREFIX + "botinfo") || args[0].equalsIgnoreCase(Info.PREFIX + "quiver")) {
            Random random = new Random();
            int randomColor = random.nextInt(0xffffff + 1);

            EmbedBuilder eb = new EmbedBuilder();

            eb.setTitle("Bot Information");
            eb.setThumbnail(Info.LOGO);
            eb.setColor(randomColor);
            eb.setDescription(":white_medium_small_square: Username: " + event.getJDA().getSelfUser().getAsMention() + "#" + event.getJDA().getSelfUser().getDiscriminator().toString()
            + "             :white_medium_small_square: Prefix: " + Info.PREFIX 
            + " \n:white_medium_small_square: User ID: " + event.getJDA().getSelfUser().getId().toString()
            + " \n:white_medium_small_square: Guild Count: " + event.getJDA().getGuilds().size()
            + " \n:white_medium_small_square: User Count: " + event.getJDA().getUsers().size()
            + " \n:white_medium_small_square: Quiver's Home Guild: [Invite](https://discord.gg/432hTd4 \"Quiver's Home Discord Server\")");
            eb.setFooter("Quiver Bot Information", Info.LOGO);

            event.getChannel().sendMessage(eb.build()).queue();
        }
    }
}