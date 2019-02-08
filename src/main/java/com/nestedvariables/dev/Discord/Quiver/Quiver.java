package com.nestedvariables.dev.Discord.Quiver;

import javax.security.auth.login.LoginException;

import com.nestedvariables.dev.Discord.Quiver.util.Data;
import com.nestedvariables.dev.Discord.Quiver.commands.test;

import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Quiver {
    public static ShardManager shardManager;

    public static void main(String[] args) throws LoginException, RateLimitedException, InterruptedException {
        Credentials credentials = new Credentials();
        DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();

        builder.setToken(credentials.token);
        builder.addEventListeners(
            new test()
        );
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setGameProvider(id -> Game.watching("Archery | Q!help | Shard Number " + id));
        builder.setShardsTotal(credentials.shards);
        shardManager = builder.build();
        System.out.print("\n\nBullseye! " + credentials.name + " is online!\n\n");

        // Load guild data
        Data data = new Data();
        data.load();
    }

}