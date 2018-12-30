package com.nestedvariables.dev.Discord.Quiver.events.serverowner;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.Utils;
import com.nestedvariables.dev.Discord.Quiver.Checks;
import com.nestedvariables.dev.Discord.Quiver.Info;
import com.nestedvariables.dev.Discord.Quiver.SQLDriver;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Toggles extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getChannel()) + "toggle")) {
            if (Checks.isServerOwner(event) || Checks.isAdministrator(event)) {
                try {
                    Connection conn = SQLDriver.getConn();
                    Statement stmt = conn.createStatement();
                    if (args.length < 2) {
                        EmbedBuilder nullArgs = new EmbedBuilder();

                        nullArgs.setDescription(
                                event.getMember().getAsMention() + ", You didn't specify which system to disable");
                        nullArgs.setColor(Info.ERROR_RED);
                        nullArgs.setFooter("Quiver Non-specified system", Info.LOGO);

                        event.getChannel().sendMessage(nullArgs.build()).queue((message) -> {
                            message.delete().queueAfter(10, TimeUnit.SECONDS);
                        });
                        // Toggle for the Channel System
                    } else if (args[1].equalsIgnoreCase("channels") || args[1].equalsIgnoreCase("channelsystem")) {

                        if(Checks.isChannelSystemEnabled(event)){
                        Random random = new Random();
                        int randomColor = random.nextInt(0xffffff + 1);
                        
                        EmbedBuilder channels = new EmbedBuilder();

                        channels.setDescription(event.getMember().getAsMention() + ", I've disabled the channel system for this guild, you can reenable it by typing the same command.");
                        channels.setColor(randomColor);
                        channels.setFooter("Quiver Disabled System", Info.LOGO);

                        stmt.execute("UPDATE `guild_options` SET `channel_system`='false' WHERE `guild_id`='" + event.getGuild().getId().toString() + "'");
                        conn.close();
                        } else {
                        Random random = new Random();
                        int randomColor = random.nextInt(0xffffff + 1);
                        
                        EmbedBuilder channels = new EmbedBuilder();

                        channels.setDescription(event.getMember().getAsMention() + ", I've enabled the channel system for this guild, you can disable it by typing the same command.");
                        channels.setColor(randomColor);
                        channels.setFooter("Quiver Enabled System", Info.LOGO);

                        stmt.execute("UPDATE `guild_options` SET `channel_system`='true' WHERE `guild_id`='" + event.getGuild().getId().toString() + "'");
                        conn.close();

                        }
                    }
                } catch (SQLException e) {

                }

            }
        }
    }
}