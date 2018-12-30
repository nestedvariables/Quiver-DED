package com.nestedvariables.dev.Discord.Quiver.events;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.nestedvariables.dev.Discord.Quiver.Utils;
import com.nestedvariables.dev.Discord.Quiver.Logger;
import com.nestedvariables.dev.Discord.Quiver.SQLDriver;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class GuildJoin extends ListenerAdapter {
    public void onGuildJoin(GuildJoinEvent event) {

        // Introduce bot in server
        EmbedBuilder introduction = new EmbedBuilder();
        introduction.setColor(Utils.embedColor("welcome"));
        introduction.setDescription("Phew, I made it over the ~waves~ intact. Oh, hey there, I'm " + event.getJDA().getSelfUser().getAsMention() + " and I'd like to thank you for adding me to your Discord server, it feels pretty toasty in here. Here's some stuff you should know about me:");
        introduction.addField("I'm known as", event.getJDA().getSelfUser().getAsMention(), true);
        introduction.addField("My prefix is", Utils.getPrefix(event.getGuild()), true);event.getJDA().getSelfUser().getAsMention();

        event.getGuild().getDefaultChannel().sendMessage(introduction.build()).queue();
        introduction.clear();

        try {
            Connection connection = SQLDriver.getConn();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM `guild_options` WHERE `guild_id`='" + event.getGuild().getId() + "'");
        
            if (result.next() == false) {
                statement.execute("INSERT INTO `guild_options`(`guild_id`, `guild_name`, `guild_lang`,`prefix`,`channel_system`) VALUES('" + event.getGuild().getId().toString() + "','" + event.getGuild().getName().toString() + "','" +  Utils.locale(event.getGuild().getRegionRaw()) + "','Q!','true')");
                System.out.println("Added Guild: " + event.getGuild().getName() + " with ID: " + event.getGuild().getId() + " to the database");
                connection.close();
            } 
            else {
                System.out.println(event.getGuild().getName() + " with ID: " + event.getGuild().getId() + " already exist in database.");
                connection.close();
            }
        }
        catch (Exception e) {
            Logger.log(e.toString());
            event.getGuild().getDefaultChannel().sendMessage("blin vadim caused an error").queue();
        }
    }
}