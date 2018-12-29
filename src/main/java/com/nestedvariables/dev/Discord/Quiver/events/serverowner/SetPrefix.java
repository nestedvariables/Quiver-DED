package com.nestedvariables.dev.Discord.Quiver.events.serverowner;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.GuildData;
import com.nestedvariables.dev.Discord.Quiver.Info;
import com.nestedvariables.dev.Discord.Quiver.SQLDriver;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class SetPrefix extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(GuildData.getPrefix(event.getGuild().getId()) + "setprefix")) {
            if (args.length < 2) {

                EmbedBuilder nullArgs = new EmbedBuilder();

                nullArgs.setDescription(event.getMember().getAsMention() + ", You didn't specify what you want your new prefix to be! \n\nUsage: " + GuildData.getPrefix(event.getGuild().getId()) + "setprefix <new prefix>");
                nullArgs.setColor(Info.ERROR_RED);
                nullArgs.setFooter("Quiver Custom Prefix Error", Info.LOGO);

                event.getChannel().sendMessage(nullArgs.build()).queue((message) -> {
                    message.delete().queueAfter(10, TimeUnit.SECONDS);
                });

            } else {

                try {
                    Connection conn = SQLDriver.getConn();
                    Statement stmt = conn.createStatement();

                    stmt.execute("UPDATE `guild_options` SET `prefix`='" + args[1].toString() + "' WHERE `guild_id`='" + event.getGuild().getId() + "'");

                    Random random = new Random();
                    int randomColor = random.nextInt(0xffffff + 1);

                    EmbedBuilder prefix = new EmbedBuilder();

                    prefix.setDescription(event.getMember().getAsMention() + ", I've changed the prefix for all future commands to " + args[1]);
                    prefix.setColor(randomColor);
                    prefix.setFooter("Quiver Custom Prefix", Info.LOGO);

                    event.getChannel().sendMessage(prefix.build()).queue((message) -> {
                        message.delete().queueAfter(10, TimeUnit.SECONDS);
                    });
                } catch (SQLException e) {

                }
            }
        }
    }
}