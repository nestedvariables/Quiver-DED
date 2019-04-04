package nestedvar.Quiver;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginException;
import nestedvar.Quiver.util.Config;
import nestedvar.Quiver.util.Data;
import nestedvar.Quiver.util.Lang;
import nestedvar.Quiver.util.Logger;
import nestedvar.Quiver.util.Resources;
import nestedvar.Quiver.arrow.ArrowHandler;
import nestedvar.Quiver.arrow.ArrowObject;
import nestedvar.Quiver.commands.Arrows;
import nestedvar.Quiver.commands.Reload;
import nestedvar.Quiver.commands.test;
import nestedvar.Quiver.events.GuildMessageReceived;
import nestedvar.Quiver.events.Ready;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

public class Quiver {
    public static ShardManager shardManager;
    public static DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();

    ArrowHandler arrow = new ArrowHandler();
    Resources resources = new Resources();
    Lang lang = new Lang();
    Data data = new Data();

    public Object[] listeners = { new test(), new Reload(), new Arrows(), new Ready(), new GuildMessageReceived() };

    /**
     * Registers all external listeners
     */
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            for (ArrowObject arrow : ArrowHandler.arrows) {
                builder.addEventListeners(arrow.listeners);
            }
        }
    });

    /**
     * Starts Quiver with settings from configuration
     * 
     * @throws LoginException
     */
    public Quiver() throws LoginException {
        new Logger(0, "⚡ Starting Quiver processes...");
        thread.run();
        Config config = new Config();
        lang.load();
        data.load();
        arrow.load();

        builder.setToken(config.get("token"));
        builder.addEventListeners(listeners);
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setGameProvider(shard -> Game.playing(config.get("status").replace("%shard%", String.valueOf(shard))));
        builder.setShardsTotal(Integer.parseInt(config.get("shards")));
        shardManager = builder.build();
        new Logger(0, "🎯 Bullseye! " + config.get("name") + " is online. " + "Using " + resources.getCPULoad() + "% of the CPU and " + resources.getRAMUsage() + " MB of memory.");
    }

    /**
     * Kills Quiver and JDA processes
     */
    public void exit() {
        builder.removeEventListeners(listeners);
        shardManager.shutdown();
        new Logger(0, "🤖 Stopped Quiver.");
    }
}