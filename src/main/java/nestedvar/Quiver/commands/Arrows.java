package nestedvar.Quiver.commands;

import nestedvar.Quiver.arrow.ArrowHandler;
import nestedvar.Quiver.arrow.ArrowObject;
import nestedvar.Quiver.util.Data;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Arrows extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        Data data = new Data();
        if (args[0].equalsIgnoreCase(data.getPrefix(event.getGuild()) + "arrows")) {
            EmbedBuilder embed = new EmbedBuilder();

            for (ArrowObject arrow : ArrowHandler.arrows) {;
                String arrows = arrow.description + "\n" + "Version " + arrow.version + " by " + arrow.author;
                embed.addField(arrow.name, arrows, false);
            }

            embed.setColor(0xf49242);
            embed.setTitle("🔌 Installed Arrows");
            event.getChannel().sendMessage(embed.build()).queue();
        }
    }
}