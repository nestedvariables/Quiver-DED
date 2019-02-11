package nestedvar.Quiver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginException;

import nestedvar.Quiver.util.Config;
import nestedvar.Quiver.util.Data;
import nestedvar.Quiver.util.Lang;
import nestedvar.Quiver.util.Logger;
import nestedvar.Quiver.util.Resources;
import nestedvar.Quiver.arrow.Arrow;
import nestedvar.Quiver.arrow.ArrowHandler;
import nestedvar.Quiver.arrow.Arrowold;
import nestedvar.Quiver.arrow.ListenerTest;
import nestedvar.Quiver.commands.Arrows;
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

    // TODO REPLACE
    public static Map<Arrow, String> arrows;

    Config config = new Config();
    ArrowHandler arrow = new ArrowHandler();
    Resources resources = new Resources();
    Lang lang = new Lang();
    Data data = new Data();

    public Object[] listeners = {
        new test(),
        new Reload(),
        new Arrows(),
        new Ready(),
    };

    /**
     * Starts Quiver with settings from configuration
     * @throws LoginException
     */
    public Quiver() throws LoginException {
        config.load();
        lang.load();
        data.load();
        arrow.load();
        
        arrows = new HashMap<Arrow, String>();

        // TODO Enable addons, move to arrow class
        

        builder.setToken(config.token());
        builder.addEventListeners(listeners);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setGameProvider(shard -> Game.playing(config.status().replace("%shard%", String.valueOf(shard))));
        builder.setShardsTotal(config.shards());

       /* for (Arrow arrow : arrows.keySet()) {
            System.out.println("ran loop");
            arrow.load();
        }*/

        shardManager = builder.build();
        new Logger(0, "🎯 Bullseye! " + config.botName() + " is online. " + "Using " + resources.getCPULoad() + "% of the CPU.");
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