package com.nestedvariables.dev.Discord.Quiver.events;

import com.nestedvariables.dev.Discord.Quiver.Logger;
import com.nestedvariables.dev.Discord.Quiver.Main;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter {

    public void onReady(ReadyEvent event) {
        Integer shardID = event.getJDA().getShardInfo().getShardId();
        String webhook = "https://canary.discordapp.com/api/webhooks/531608886109667328/Ynfb9FDGMikJz3QE-RqUaj4nirTHnCZ-PrI4IoxIgQNkk1AJVUxCwT5PW6W0PfmaAjH8";
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {

            HttpPost post = new HttpPost(webhook);
            StringEntity params = new StringEntity("details={\"embeds\":[{\"title\": \"Shard Spawned!\",\"description\": \"**Shard ID:** \"" + shardID + "\"\n**Shard Guild Count:** \"" + event.getGuildTotalCount() + "\"\n**Shard User Count:** \"" + event.getJDA().getUsers().size() + "\"\n**Guild Total:** \"" + Main.shardManager.getGuilds().size() + "\"\n**User Total:** \"" + Main.shardManager.getUsers().size(), "\"color\": 6212863,\"footer\": {\"text\" : \"Quiver Shard Spawned\",\"icon_url\": \"https://raw.githubusercontent.com/NestedVariables/Quiver/master/Quiver.png\"}]}");
            post.addHeader("content-type", "application/json");
            post.setEntity(params);
            HttpResponse response = httpClient.execute(post);


            /*
            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle("Shard Spawned!");
            embed.setColor(0x5eccff);
            embed.setDescription("**Shard ID:** " + shardID + "\n**Shard Guild Count:** " + event.getGuildTotalCount()
                    + "\n**Shard User Count:** " + event.getJDA().getUsers().size() + "\n**Guild Total:** "
                    + Main.shardManager.getGuilds().size() + "\n**User Total:** "
                    + Main.shardManager.getUsers().size());
            embed.setFooter("Quiver Shard Spawn", Info.LOGO);
            */
            shardID = null;
        } catch (Exception e) {
            e.printStackTrace();
            Logger.log("fatal", e.toString(), null);
        }

    }
}