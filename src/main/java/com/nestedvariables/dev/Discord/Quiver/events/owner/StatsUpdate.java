package com.nestedvariables.dev.Discord.Quiver.events.owner;

import com.nestedvariables.dev.Discord.Quiver.Logger;
import com.nestedvariables.dev.Discord.Quiver.Main;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class StatsUpdate extends ListenerAdapter {

    public void onReady(ReadyEvent event) {
        try{
        Integer guildCount = Main.shardManager.getGuilds().size();
        Integer shardCount = Main.shardManager.getShardsTotal();
        Integer userCount = Main.shardManager.getUsers().size();

            // Shard Channel
            Main.shardManager.getGuildById("488137783127572491").getVoiceChannelById("489246383140896778").getManager().setName("Shard Count: " + shardCount).queue();
            // User Channel
            Main.shardManager.getGuildById("488137783127572491").getVoiceChannelById("489246451181027328").getManager().setName("User Count: " + userCount).queue();
            // Guild Channel
            Main.shardManager.getGuildById("488137783127572491").getVoiceChannelById("489246268313436180").getManager().setName("Guild Count: " + guildCount).queue();

        } catch (Exception e) {
            Guild guild = null;
            Logger.log("fatal", e.toString(), guild);
        }

    }

    public void onGuildJoin(GuildJoinEvent event) {
        try{
            Integer guildCount = Main.shardManager.getGuilds().size();
            Integer shardCount = Main.shardManager.getShardsTotal();
            Integer userCount = Main.shardManager.getUsers().size();
    
                // Shard Channel
                Main.shardManager.getGuildById("488137783127572491").getVoiceChannelById("489246383140896778").getManager().setName("Shard Count: " + shardCount).queue();
                // User Channel
                Main.shardManager.getGuildById("488137783127572491").getVoiceChannelById("489246451181027328").getManager().setName("User Count: " + userCount).queue();
                // Guild Channel
                Main.shardManager.getGuildById("488137783127572491").getVoiceChannelById("489246268313436180").getManager().setName("Guild Count: " + guildCount).queue();
    
            } catch (Exception e) {
                Logger.log("fatal", e.toString(), event.getGuild());
            }
    }

    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        
        try{
            Integer guildCount = Main.shardManager.getGuilds().size();
            Integer shardCount = Main.shardManager.getShardsTotal();
            Integer userCount = Main.shardManager.getUsers().size();
    
                // Shard Channel
                Main.shardManager.getGuildById("488137783127572491").getVoiceChannelById("489246383140896778").getManager().setName("Shard Count: " + shardCount).queue();
                // User Channel
                Main.shardManager.getGuildById("488137783127572491").getVoiceChannelById("489246451181027328").getManager().setName("User Count: " + userCount).queue();
                // Guild Channel
                Main.shardManager.getGuildById("488137783127572491").getVoiceChannelById("489246268313436180").getManager().setName("Guild Count: " + guildCount).queue();
    
            } catch (Exception e) {
                Logger.log("fatal", e.toString(), event.getGuild());
            }
    }

    public void onGuildMemberLeave(GuildMemberLeaveEvent event){
        
        try{
            Integer guildCount = Main.shardManager.getGuilds().size();
            Integer shardCount = Main.shardManager.getShardsTotal();
            Integer userCount = Main.shardManager.getUsers().size();
    
                // Shard Channel
                Main.shardManager.getGuildById("488137783127572491").getVoiceChannelById("489246383140896778").getManager().setName("Shard Count: " + shardCount).queue();
                // User Channel
                Main.shardManager.getGuildById("488137783127572491").getVoiceChannelById("489246451181027328").getManager().setName("User Count: " + userCount).queue();
                // Guild Channel
                Main.shardManager.getGuildById("488137783127572491").getVoiceChannelById("489246268313436180").getManager().setName("Guild Count: " + guildCount).queue();
    
            } catch (Exception e) {
                Logger.log("fatal", e.toString(), event.getGuild());
            }
    }

}