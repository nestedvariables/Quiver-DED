package nestedvar.Quiver;

import javax.security.auth.login.LoginException;

import nestedvar.Quiver.Misc.*;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Quiver {
    static ShardManager manager;
    static final DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
    public static void main(String[] args) throws LoginException, RateLimitedException, InterruptedException{
        builder.setToken(System.getenv("QUIVERTOKEN"));
        builder.addEventListeners(
            new Ready()
        );
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setGame(Game.watching("the loading bar fill!"));
        builder.setShardsTotal(4);

        manager = builder.build();

    }

}
