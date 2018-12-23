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
            
            System.out.println(region);
            switch (region) {
                case "us-east":
                    defaultLang = "en_US";
                case "us-west":
                    defaultLang = "en_US";
                case "us-central":
                    defaultLang = "en_US";
                case "us-south":
                    defaultLang = "en_US";
                case "brazil":
                    defaultLang = "en_US";
                case "eu-central":
                    defaultLang = "en_UK";
                case "hongkong":
                    defaultLang = "en_US";
                case "japan":
                    defaultLang = "en_US";
                case "russia":
                    defaultLang = "ru_RU";
                case "singapore":
                    defaultLang = "en_US";
                case "southafrica":
                    defaultLang = "en_UK";
                case "sydney":
                    defaultLang = "en_UK";
                case "eu-west":
                    defaultLang = "en_UK";
            }
            
            Connection conn = SQLDriver.getConn();
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM `guild_options` WHERE `guild_id`='" + event.getGuild().getId() + "'");
            
            if (rs.next() == false) {
                stmt.execute("INSERT INTO `guild_options`(`guild_id`, `guild_name`, `guild_lang`,`prefix`,`channel_system`) VALUES('" + event.getGuild().getId().toString() + "','" + event.getGuild().getName().toString() + "','" + defaultLang + "','Q!','true')");
                System.out.println("Added Guild: " + event.getGuild().getName() + " with ID: " + event.getGuild().getId() + " to the database");
                conn.close();
            } else {
                System.out.println("7");
                System.out.println("Guild: " + event.getGuild().getName() + " with ID: " + event.getGuild().getId()
                        + " already exist in database not adding to database");
                        System.out.println("8");
                conn.close();
            }
        } catch (SQLException sqle) {
        }

    }

}