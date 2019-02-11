package nestedvar.Quiver.commands;

import java.io.File;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class test extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase("tits")) {
            event.getChannel().sendMessage("here are some ascii tits :)").queue();
            File tits = new File("fat.txt");
            event.getChannel().sendFile(tits, "fat.txt").queue();
        }
    }
}