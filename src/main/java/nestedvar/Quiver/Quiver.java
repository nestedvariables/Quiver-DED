package nestedvar.Quiver;

import javax.security.auth.login.LoginException;

import nestedvar.Quiver.util.Config;
import nestedvar.Quiver.util.Data;
import nestedvar.Quiver.util.Lang;
import nestedvar.Quiver.util.Resources;
import nestedvar.Quiver.arrow.ArrowHandler;
import nestedvar.Quiver.commands.Reload;
import nestedvar.Quiver.commands.test;
import nestedvar.Quiver.events.Ready;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

public class Quiver {
    public static ShardManager shardManager;
    public static DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();

    public static void main(String[] args) throws LoginException, RateLimitedException, InterruptedException {
        // Load configuration
        Config config = new Config();
        config.load();

        builder.setToken(config.token());
        builder.addEventListeners(
            new test(),
            new Reload(),
            new Ready()
        );
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setGameProvider(shard -> Game.playing(config.status().replace("%shard%", String.valueOf(shard))));
        builder.setShardsTotal(config.shards());

        // Enable arrow modules
        ArrowHandler arrows = new ArrowHandler();
        arrows.load();
        
        // Prepare language files
        Lang lang = new Lang();
        lang.load();

        // Load guild data
        Data data = new Data();
        data.load();

        // Check Resource usage
        Resources resources = new Resources();

        shardManager = builder.build();
        System.out.print("\n🎯 Bullseye! " + config.botName() + " is online. " + 
            "Using " + resources.getCPULoad() + "% of the CPU.\n\n");
    }
}