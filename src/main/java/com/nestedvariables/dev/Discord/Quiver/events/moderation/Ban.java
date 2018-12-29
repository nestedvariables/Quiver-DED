package com.nestedvariables.dev.Discord.Quiver.events.moderation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.GuildData;
import com.nestedvariables.dev.Discord.Quiver.Info;
import com.nestedvariables.dev.Discord.Quiver.SQLDriver;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Ban extends ListenerAdapter {

    String banID;
    String banReason = "";
    Integer oldID;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        EmbedBuilder error = new EmbedBuilder();

        error.setDescription("An internal server error occured on our end. \nPlease join our [Support Server](https://discord.gg/p9xj9UD \"Quiver Support Discord Server\")");
        error.setColor(Info.ERROR_RED);
        error.setFooter("Quiver System Error", Info.LOGO);

        if (args[0].equalsIgnoreCase(GuildData.getPrefix(event.getGuild().getId()) + "ban")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
            Date date = new Date();
            event.getMessage().delete().queue();
            if (event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
                if (args.length < 2) {
                    EmbedBuilder nullUser = new EmbedBuilder();

                    nullUser.setDescription(":white_medium_small_square: " + event.getMember().getAsMention()
                            + ", you didn't specify which member to ban!");
                    nullUser.setColor(Info.ERROR_RED);
                    nullUser.setFooter("Quiver Undefined Member", Info.LOGO);

                    event.getChannel().sendMessage(nullUser.build()).queue((message) -> {
                        message.delete().queueAfter(10, TimeUnit.SECONDS);
                    });
                } else if (args.length < 3) {
                    try {
                        Connection conn = SQLDriver.getConn();
                        Statement stmt = conn.createStatement();

                        Random random = new Random();
                        int randomColor = random.nextInt(0xffffff + 1);

                        Member memberToBan = event.getMessage().getMentionedMembers().get(0);

                        ResultSet rs = stmt.executeQuery("SELECT * FROM `bans`");
                        while (rs.next())
                            banID = rs.getString("`ban_id`");
                            
                        if (banID == null) {
                            oldID = 0;
                        }

                        Integer newBanID = oldID + 1;

                        while (rs.next())
                            stmt.execute(
                                    "INSERT INTO `bans`(`discord_id` , `discord_username` , `ban_id` , `ban_reason` , `guild_name` , `guild_id`,`ban_executor`,`ban_executor_id`)"
                                            + "VALUES('" + memberToBan.getUser().getId().toString() + "','"
                                            + memberToBan.getUser().getName().toString() + "#"
                                            + memberToBan.getUser().getDiscriminator().toString() + "' , '" + newBanID.toString()
                                            + "','" + "Ban Executor Didn\'t Specify a Reason" + "','"
                                            + event.getGuild().getName().toString() + "','"
                                            + event.getMember().getUser().getId().toString() + "')");

                        event.getGuild().getController()
                                .ban(memberToBan, 7, "Ban ID: " + banID + "| Ban executor didn't specify a reason")
                                .queue();

                        EmbedBuilder banNoReason = new EmbedBuilder();

                        banNoReason.setAuthor("Ban ID | " + banID, "https://nestedvariables.tk/quiver/bans/" + banID,
                                memberToBan.getUser().getAvatarUrl());
                        banNoReason.setColor(randomColor);
                        banNoReason.addField("Banned Member: ", memberToBan.getEffectiveName(), false);
                        banNoReason.addField("Ban Executor: ", event.getMember().getEffectiveName(), false);
                        banNoReason.addField("Reason: ", "Ban executor didn't specify a reason", false);
                        banNoReason.setFooter("Quiver Ban Report", Info.LOGO);

                        event.getChannel().sendMessage(banNoReason.build()).queue((message) -> {
                            message.delete().queueAfter(30, TimeUnit.SECONDS);
                        });
                        conn.close();
                    } catch (SQLException sqle) {
                        event.getJDA().getGuildById("488137783127572491").getTextChannelById("517756124846358529")
                                .sendMessage(event.getJDA().getGuildById("488137783127572491")
                                        .getRoleById("489269871306080257").getAsMention()
                                        + "\n A ban command failed on the guild: "
                                        + event.getGuild().getName().toString() + " with the ID: "
                                        + event.getGuild().getId().toString() + "\n Reason:" + sqle.toString())
                                .queue();
                    }
                } else {
                    try {
                        Connection conn = SQLDriver.getConn();
                        Statement stmt = conn.createStatement();

                        Random random = new Random();
                        int randomColor = random.nextInt(0xffffff + 1);

                        Member memberToBan = event.getMessage().getMentionedMembers().get(0);

                        for (int i = 2; i < args.length; i++) {
                            banReason = banReason + args[i] + " ";
                        }

                        ResultSet rs = stmt.executeQuery("SELECT * FROM `bans`");
                        while (rs.next())
                            banID = rs.getString(3);

                            if (banID == null) {
                                oldID = 0;
                            }
    
                            Integer newBanID = oldID + 1;

                        while (rs.next())
                            stmt.execute(
                                    "INSERT INTO `bans`(`discord_id` , `discord_username` , `ban_id` , `ban_reason` , `guild_name` , `guild_id` , `ban_date`)"
                                            + "VALUES('" + memberToBan.getUser().getId().toString() + "','"
                                            + memberToBan.getUser().getName().toString() + "#"
                                            + memberToBan.getUser().getDiscriminator().toString() + "' , '" + newBanID.toString()
                                            + "','" + banReason + "','" + event.getGuild().getName().toString() + "','"
                                            + event.getMember().getUser().getId().toString() + "','"
                                            + event.getGuild().getId().toString() + "','" + dateFormat.format(date)
                                            + "'");

                        event.getGuild().getController().ban(memberToBan, 7, "Ban ID: " + banID + " | " + banReason)
                                .queue();

                        EmbedBuilder banWithReason = new EmbedBuilder();

                        banWithReason.setAuthor("Ban ID | " + banID, "https://nestedvariables.tk/quiver/bans/" + banID,
                                memberToBan.getUser().getAvatarUrl());
                        banWithReason.setColor(randomColor);
                        banWithReason.addField("Banned Member: ", memberToBan.getEffectiveName(), false);
                        banWithReason.addField("Ban Executor: ", event.getMember().getEffectiveName(), false);
                        banWithReason.addField("Reason: ", banReason, false);
                        banWithReason.setFooter("Quiver Ban Report", Info.LOGO);

                        event.getChannel().sendMessage(banWithReason.build()).queue((message) -> {
                            message.delete().queueAfter(30, TimeUnit.SECONDS);
                        });

                        banReason = "";
                        conn.close();
                    } catch (SQLException sqle) {
                        event.getChannel().sendMessage(error.build()).queue((message) -> {
                            message.delete().queueAfter(10, TimeUnit.SECONDS);
                        });
                        event.getJDA().getGuildById("488137783127572491").getTextChannelById("517756124846358529")
                                .sendMessage(event.getJDA().getGuildById("488137783127572491")
                                        .getRoleById("489269871306080257").getAsMention()
                                        + "\n A ban command failed on the guild: "
                                        + event.getGuild().getName().toString() + " with the ID: "
                                        + event.getGuild().getId().toString() + "\n Reason:" + sqle.toString())
                                .queue();
                    }
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