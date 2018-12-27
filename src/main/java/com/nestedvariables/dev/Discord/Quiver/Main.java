package com.nestedvariables.dev.Discord.Quiver;

/*import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
*/

import javax.security.auth.login.LoginException;

import com.nestedvariables.dev.Discord.Quiver.events.announcements.*;
import com.nestedvariables.dev.Discord.Quiver.events.channelsystem.*;
import com.nestedvariables.dev.Discord.Quiver.events.guildjoinevents.*;
import com.nestedvariables.dev.Discord.Quiver.events.information.*;
import com.nestedvariables.dev.Discord.Quiver.events.misc.*;
import com.nestedvariables.dev.Discord.Quiver.events.moderation.*;
import com.nestedvariables.dev.Discord.Quiver.events.music.*;
import com.nestedvariables.dev.Discord.Quiver.events.owner.*;
import com.nestedvariables.dev.Discord.Quiver.events.serverowner.*;

import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Main {

    static String botName = "Quiver";
    static Integer shardCount = -1;
    //public static HashMap<String, String> prefixes = new HashMap<String, String>();

    public static void main(String[] args) throws LoginException, RateLimitedException, InterruptedException {
        DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();

        // Fill up prefixes hashmap
        /*
        Connection conn = SQLDriver.getConn();
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM `guild_options` WHERE `guild_id`=" + event.getGuild().getId());

        */

        builder.setToken(args[0]);

        builder.addEventListeners(
            // Announcement Event Listeners
            new MemberJoin(),
            
            // Channel System Listeners
            new ChannelCleanup(),
            new ChannelCreate(),
            new ChannelInvite(),
            

            // Guild Join Event Listeners
            new CreateOptionsTable(),
            new JoinAnnouncement(),

            // Information Event Listeners
            new BotInfo(),
            new Help(),
            new PermissionHelp(),
            new ServerInfo(),
            new UserInfo(),

            // Moderation Event Listeners
            new Ban(),
            new Clear(),
            new Kick(),
            new Mute(),
            new Softban(),
            new Unmute(),
            
            // Music Event Listeners
            new ClearQueue(),
            new Join(),
            new Leave(),
            new Loop(),
            new Lyrics(),
            new NowPlaying(),
            new Pause(),
            new PauseLeave(),
            new Play(),
            new Queue(),
            new QueueLoop(),
            new QueueMove(),
            new QueueRemove(),
            new QueueSkip(),
            new RemoveDupes(),
            new Replay(),
            new Resume(),
            new Search(),
            new Settings(),
            new Skip(),
            new Soundcloud(),
            new Stop(),
            new TimeSkip(),
            new Twitch(),
            new Volume(),

            // Bot Owner Event Listners
            new Announcement(),
            new BlacklistMember(),
            new CodeBlock(),
            new ErrorTest(),

            // Server Owner Event Listeners
            new LeaveGuild(),
            new SetPrefix(),
            new Toggles()

        );

        builder.setStatus(OnlineStatus.ONLINE);
        builder.setGameProvider(id -> Game.watching("Archery | " + Info.PREFIX + "help | Shard Number " + id));
        builder.setShardsTotal(shardCount);

        builder.build();

        System.out.print("Bullseye! " + botName + " is online!");

    }

}