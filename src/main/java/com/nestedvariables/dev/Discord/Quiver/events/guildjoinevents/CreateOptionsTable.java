package com.nestedvariables.dev.Discord.Quiver.events.guildjoinevents;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nestedvariables.dev.Discord.Quiver.SQLDriver;

import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CreateOptionsTable extends ListenerAdapter {

    public void onGuildJoin(GuildJoinEvent event) {
        String region = event.getGuild().getRegionRaw();
        String defaultLang = "en_US";
        try {          

            if (region.equalsIgnoreCase("us-east")) {
                defaultLang = "en_US";
            } else if (region.equalsIgnoreCase("us-west")) {
                defaultLang = "en_US";
            } else if (region.equalsIgnoreCase("us-central")) {
                defaultLang = "en_US";
            } else if (region.equalsIgnoreCase("us-south")) {
                defaultLang = "en_US";
            } else if (region.equalsIgnoreCase("brazil")) {

            } else if (region.equalsIgnoreCase("eu-central")) {
                defaultLang = "en_UK";
            } else if (region.equalsIgnoreCase("hongkong")) {

            } else if (region.equalsIgnoreCase("japan")) {

            } else if (region.equalsIgnoreCase("russia")) {
                defaultLang = "ru_RU";
            } else if (region.equalsIgnoreCase("singapore")) {

            } else if (region.equalsIgnoreCase("southafrica")) {
                defaultLang = "en_UK";
            } else if (region.equalsIgnoreCase("sydney")) {
                defaultLang = "en_UK";
            } else if (region.equalsIgnoreCase("eu-west")) {
                defaultLang = "en_UK";
            }

            Connection conn = SQLDriver.getConn();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM `guild_options` WHERE `guild_id`='" + event.getGuild().getId() + "'");

            if (rs == null) {
                stmt.execute("INSERT INTO `guild_options`(`guild_id`, `guild_name`, `guild_lang`) VALUES('" + event.getGuild().getId().toString() + "','" + event.getGuild().getName().toString() + "','" + defaultLang + "','q!')");
            } else {
                System.out.println("Guild: " + event.getGuild().getName() + " with ID: " + event.getGuild().getId() + " already exist in database not adding to database");            }

        } catch (SQLException sqle) {

        }

    }

}