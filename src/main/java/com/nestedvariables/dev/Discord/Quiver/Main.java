package com.nestedvariables.dev.Discord.Quiver;

import javax.security.auth.login.LoginException;

import com.nestedvariables.dev.Discord.Quiver.events.announcements.*;
import com.nestedvariables.dev.Discord.Quiver.events.channelsystem.*;
import com.nestedvariables.dev.Discord.Quiver.events.guildjoinevents.*;
import com.nestedvariables.dev.Discord.Quiver.events.information.*;
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
    public static Integer shardCount = -1;

    public static void main(String[] args) throws LoginException, RateLimitedException, InterruptedException {
        DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();

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

            // Misc Event Listeners
            

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
            new StatsUpdate(),
            new Unblacklist(),

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