package nestedvar.Quiver;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import javax.security.auth.login.LoginException;

import nestedvar.Quiver.util.Config;
import nestedvar.Quiver.util.Data;
import nestedvar.Quiver.util.Lang;
import nestedvar.Quiver.util.Resources;
import nestedvar.Quiver.arrow.ArrowHandler;
import nestedvar.Quiver.commands.Reload;
import nestedvar.Quiver.commands.test;

import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Quiver {
    public static ShardManager shardManager;
    public static DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();

    public static void main(String[] args) throws LoginException, RateLimitedException, InterruptedException {
        Credentials credentials = new Credentials();

        // Load configuration
        Config config = new Config();
        config.load();

        builder.setToken(credentials.token);
        builder.addEventListeners(
            new test(),
            new Reload()
        );
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setGameProvider(shard -> Game.watching(config.status().replace("%shard%", String.valueOf(shard))));
        builder.setShardsTotal(config.shards());

        // Enable arrow modules
        ArrowHandler arrows = new ArrowHandler();
        arrows.load();
        
        // Prepare language files
        Lang lang = new Lang();
        lang.load();

        // Check Resource usage
        Resources resources = new Resources();

        shardManager = builder.build();
        System.out.print("\n🎯 Bullseye! " + config.botName() + " is online. " + 
            "Using " + resources.getCPULoad() + "% of the CPU.\n\n");

        // Load guild data
        Data data = new Data();
        data.load();
    }

}