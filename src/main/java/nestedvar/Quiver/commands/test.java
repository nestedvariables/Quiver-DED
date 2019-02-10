package nestedvar.Quiver.commands;
import nestedvar.Quiver.util.Data;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class test extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
/*
        if (args[0].equalsIgnoreCase(data.getPrefix(event.getGuild()) + "testmsg")) {
            event.getChannel().sendMessage(lang.getMessage(event.getGuild(), "testmsg")).queue();
        }
        else if (args[0].equalsIgnoreCase(data.getPrefix(event.getGuild()) + "error")) {
            if (args[1].equalsIgnoreCase("fatal")) {
                data.getPrefix(event.getJDA().getGuildById("523301176309972993"));
            }
            else {
                Logger.log("fatal", "vadim went into blender", event.getGuild());
            }
        }
        else if (args[0].equals("~ispremium")) {
            event.getChannel().sendMessage("Guild owner: " + event.getGuild().getOwner().getUser().getName()).queue();
            event.getChannel().sendMessage(String.valueOf(Utils.isPremium(event.getGuild()))).queue();
        }
        */

        if (args[0].equalsIgnoreCase("~test")) {
            Data data = new Data();
            //System.out.println(data.getPrefix(event.getGuild()));
            System.out.println(data.getDefaultLocale(event.getGuild()));
        }
    }
}