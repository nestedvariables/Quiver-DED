package com.nestedvariables.dev.Discord.Quiver.events.owner;

<<<<<<< HEAD
import com.nestedvariables.dev.Discord.Quiver.Bools;
import com.nestedvariables.dev.Discord.Quiver.Utils;
=======
import com.nestedvariables.dev.Discord.Quiver.Checks;
import com.nestedvariables.dev.Discord.Quiver.GuildData;
>>>>>>> 203eb1627be08835dfa105763360d87eb25d1321

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Unblacklist extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
<<<<<<< HEAD
        if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + "unblacklist")) {
            if(Bools.isBotOwner(event)) {
=======
        if (args[0].equalsIgnoreCase(GuildData.getPrefix(event.getGuild()) + "unblacklist")) {
            if(Checks.isBotOwner(event)) {
>>>>>>> 203eb1627be08835dfa105763360d87eb25d1321
                
            }
        }
    }
}