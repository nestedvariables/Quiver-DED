package com.nestedvariables.dev.Discord.Quiver;

import javax.security.auth.login.LoginException;

import com.nestedvariables.dev.Discord.Quiver.commands.test;
import com.nestedvariables.dev.Discord.Quiver.events.GuildJoin;
import com.nestedvariables.dev.Discord.Quiver.events.ReadyListener;
import com.nestedvariables.dev.Discord.Quiver.events.announcements.*;
import com.nestedvariables.dev.Discord.Quiver.events.channelsystem.*;
import com.nestedvariables.dev.Discord.Quiver.events.information.*;
import com.nestedvariables.dev.Discord.Quiver.events.moderation.*;
import com.nestedvariables.dev.Discord.Quiver.events.owner.*;
import com.nestedvariables.dev.Discord.Quiver.commands.Help;
import com.nestedvariables.dev.Discord.Quiver.commands.Settings;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Main {

    public static String botName = "Quiver";
    // For the most part set this to -1 
    // Only change this if you ABSOLUTELY 100% know what you are doing
    public static Integer shardCount = -1;
    public static ShardManager shardManager;

    public static void main(String[] args) throws LoginException, RateLimitedException, InterruptedException {
        DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();

        // Fetch token from token file
        builder.setToken(Tokens.token);

        builder.addEventListeners(

                new Blacklist(),
                new Codeblocks(),
                new Clear(),
                new GuildJoin(),
                new Help(),
                new List(),
                new ReadyListener(),
                new Settings(),
                new Unblacklist()

        );

        builder.setStatus(OnlineStatus.ONLINE);
        builder.setGameProvider(id -> Game.watching("Archery | Q!help | Shard Number " + id));
        builder.setShardsTotal(shardCount);

        shardManager = builder.build();

        System.out.print("\n\nBullseye! " + botName + " is online!\n\n");

        // Load all necessary data for guilds
        Utils.loadData();
    }

}