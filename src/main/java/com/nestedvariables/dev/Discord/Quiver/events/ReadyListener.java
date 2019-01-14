package com.nestedvariables.dev.Discord.Quiver.events;

import java.awt.Color;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.nestedvariables.dev.Discord.Quiver.Logger;
import com.nestedvariables.dev.Discord.Quiver.Main;

import ca.momoperes.canarywebhooks.DiscordMessage;
import ca.momoperes.canarywebhooks.WebhookClient;
import ca.momoperes.canarywebhooks.WebhookClientBuilder;
import ca.momoperes.canarywebhooks.embed.DiscordEmbed;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter {

    public void onReady(ReadyEvent event) {
        Integer shardID = event.getJDA().getShardInfo().getShardId();
        String webhook = "https://canary.discordapp.com/api/webhooks/531608886109667328/Ynfb9FDGMikJz3QE-RqUaj4nirTHnCZ-PrI4IoxIgQNkk1AJVUxCwT5PW6W0PfmaAjH8";
        try{
        WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build();

        DiscordEmbed embed = new DiscordEmbed.Builder()
        .withTitle("Shard Spawned!")
        .withColor(Color.getColor("#5eccff"))
        .withDescription("**Shard ID:** " + shardID + "\n**Shard Guild Count:** " + event.getGuildTotalCount() + "\n**Shard User Count:** " + event.getJDA().getUsers().size() + "\n**Guild Total:** " + Main.shardManager.getGuilds().size() + "\n**User Total:** " + Main.shardManager.getUsers().size())
        .build();

        DiscordMessage message = new DiscordMessage.Builder(null)
        .withEmbed(embed)
        .build();

        try {
        client.sendPayload(message);
        } catch(IOException e){
            Logger.log("fatal", e.toString(), null);
        }

        } catch(URISyntaxException e){
            Logger.log("fatal", e.toString(), null);
        }
        shardID = null;
    }

}