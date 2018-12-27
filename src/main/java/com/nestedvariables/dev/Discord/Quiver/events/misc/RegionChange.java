package com.nestedvariables.dev.Discord.Quiver.events.misc;

import net.dv8tion.jda.core.events.guild.update.GuildUpdateRegionEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class RegionChange extends ListenerAdapter{

    public void onGuildUpdateRegion(GuildUpdateRegionEvent event) {
        System.out.print("Guild: " + event.getGuild().getName() + " updated their region to " + event.getNewRegionRaw().toString() + "\nOld Region was: " + event.getOldRegionRaw().toString());
    }

}