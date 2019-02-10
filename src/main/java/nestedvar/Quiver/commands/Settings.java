package nestedvar.Quiver.commands;

import nestedvar.Quiver.util.Data;
import nestedvar.Quiver.util.Embeds;
import nestedvar.Quiver.util.Lang;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Settings extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        Embeds embed = new Embeds();
        Data data = new Data();
        Lang lang = new Lang();
        
        if (args[0].equalsIgnoreCase(data.getPrefix(event.getGuild()) + "set")) {
            if (args.length < 2) event.getChannel().sendMessage(embed.usage(event, args[0]).build()).queue();
            else if (args[1].equalsIgnoreCase(lang.getMessage(event.getGuild(), "prefixSubcommand"))) {
                if (args.length < 3) event.getChannel().sendMessage(embed.usage(event, args[1]).build()).queue();
                else {
                    data.setPrefix(event.getGuild(), args[2]);
                    event.getChannel().sendMessage(embed.prefixSetSuccess(event).build()).queue();
                }   
            }
            else if (args[1].equalsIgnoreCase(lang.getMessage(event.getGuild(), "localeSubcommand"))) {
                if (args.length < 3) event.getChannel().sendMessage(embed.localeUsage(event, args[1]).build()).queue();
                else event.getChannel().sendMessage(embed.localeSetSuccess(event).build()).queue();
            }
        }
    }
}