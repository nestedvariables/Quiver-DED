package nestedvar.Quiver.commands;

import nestedvar.Quiver.Quiver;
import nestedvar.Quiver.arrow.ArrowHandler;
import nestedvar.Quiver.arrow.ArrowObject;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class test extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase("~load")) {
            for (ArrowObject arrow : ArrowHandler.arrows) {
                Quiver.builder.addEventListeners(arrow.listeners);
            }
        }
        else if (args[0].equalsIgnoreCase("~unload")) {
            for (ArrowObject arrow : ArrowHandler.arrows) {
                Quiver.builder.removeEventListeners(arrow.listeners);
            }
        }
    }
}