package dev.nestedvar.Quiver;

import javax.security.auth.login.LoginException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.nestedvar.Quiver.listener.Ready;
import dev.nestedvar.Quiver.util.Resources;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.kyori.text.serializer.gson.GsonComponentSerializer;

public class Quiver {
    static ShardManager manager;
    static Resources res = new Resources();
    static final DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();

    public static final Gson GSON = GsonComponentSerializer.populate(new GsonBuilder()).create();

    public static void main(String[] args) throws LoginException {
        builder.setToken(System.getenv("QUIVER_TOKEN"));
        builder.addEventListeners(
            new Ready()
        );
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setGame(Game.watching("the loading bar fill!"));
        builder.setShardsTotal(4);

        manager = builder.build();

        System.out.println("Bullseye Quiver is online!\n\nCurrently using " + res.getCPULoad() + "% CPU and " + res.getRAMUsage() + "MB of RAM");
    }

}
