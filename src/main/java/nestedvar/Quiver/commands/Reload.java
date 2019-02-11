package nestedvar.Quiver.commands;

import nestedvar.Quiver.Launcher;
import nestedvar.Quiver.util.Data;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Reload extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        Data data = new Data();
        // TODO add command to locales
        if (args[0].equalsIgnoreCase(data.getPrefix(event.getGuild()) + "reload")) {
            //if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) return;
            Launcher.restart();
            /*if (args.length < 2) {
                // reload shit
                event.getChannel().sendMessage("shit").queue();
                handler.reload();
            }
            else {
                handler.unload();
                event.getChannel().sendMessage("unloaded").queue();
            }*/
        }
    }
}