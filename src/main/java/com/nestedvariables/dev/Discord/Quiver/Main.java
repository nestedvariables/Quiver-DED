package com.nestedvariables.dev.Discord.Quiver;

import javax.security.auth.login.LoginException;

import com.nestedvariables.dev.Discord.Quiver.events.moderation.*;
import com.nestedvariables.dev.Discord.Quiver.events.music.*;
import com.nestedvariables.dev.Discord.Quiver.events.owner.*;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Main {

    static String token = System.console().readLine("Insert Token  \n");
    public static JDABuilder jda;

    public static void main(String[] args) throws LoginException, RateLimitedException, InterruptedException {
        jda = new JDABuilder(AccountType.BOT).setToken(token);

        jda.setStatus(OnlineStatus.ONLINE);
        jda.setGame(Game.watching("Archery | " + Info.PREFIX + "help"));
        
        // Adding Event Listeners
        // Music Event Listeners
        jda.addEventListener(new ClearQueue());
        jda.addEventListener(new Join());
        jda.addEventListener(new Leave());
        jda.addEventListener(new Loop());
        jda.addEventListener(new Lyrics());
        jda.addEventListener(new NowPlaying());
        jda.addEventListener(new Pause());
        jda.addEventListener(new PauseLeave());
        jda.addEventListener(new Play());
        jda.addEventListener(new Queue());
        jda.addEventListener(new QueueLoop());
        jda.addEventListener(new QueueMove());
        jda.addEventListener(new QueueRemove());                
        jda.addEventListener(new QueueSkip()); 
        jda.addEventListener(new RemoveDupes());
        jda.addEventListener(new Replay());
        jda.addEventListener(new Resume());
        jda.addEventListener(new Search());
        jda.addEventListener(new Settings());
        jda.addEventListener(new Skip());
        jda.addEventListener(new Soundcloud());
        jda.addEventListener(new Stop());
        jda.addEventListener(new TimeSkip());
        jda.addEventListener(new Twitch());
        jda.addEventListener(new Volume());
        // End of Music Event Listeners
        // Moderation Event Listeners
        jda.addEventListener(new Ban());
        jda.addEventListener(new Clear());
        jda.addEventListener(new Kick());
        jda.addEventListener(new Mute());
        jda.addEventListener(new Softban());
        jda.addEventListener(new Unban());
        jda.addEventListener(new Unmute());
        // End of Moderation Event Listeners
        // Bot Owner Event Listeners 
        jda.addEventListener(new Announcement());
        jda.addEventListener(new JoinAnnouncement());
        // End of Bot Owner Event Listeners
        //Done Adding Event Listeners



        for (int i = 0; i < 2; i++) {
			jda.useSharding(i, 2).build();
        }
        
        System.out.print("Bullseye! Quiver is online!");


    }

}