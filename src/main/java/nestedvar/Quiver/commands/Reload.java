package nestedvar.Quiver.commands;

import nestedvar.Quiver.arrow.ArrowHandler;
import nestedvar.Quiver.util.Data;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Reload extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        Data data = new Data();
        // TODO add command to locales
        if (args[0].equalsIgnoreCase(data.getPrefix(event.getGuild()) + "reload")) {
            //if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) return;
            System.out.println(data.getPrefix(event.getGuild()));

            // reload shit
            event.getChannel().sendMessage("shit").queue();
            ArrowHandler handler = new ArrowHandler();
            handler.reload();
        }
    }
}