package nestedvar.Quiver.commands;

import java.util.HashMap;
import java.util.Map;

import nestedvar.Quiver.arrow.ArrowHandler;
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

            String arrows = "";
            for (String arrow : ArrowHandler.arrows) {;
                HashMap<String, String> temp = ArrowHandler.arrowInfo.get(arrow);
                arrows += temp.get("description") + "\n" + "Version " + temp.get("version") + " by " + temp.get("author");
                embed.addField(arrow, arrows, false);
            }

            embed.setColor(0xf49242);
            embed.setTitle("🔌 Installed Arrows");
            event.getChannel().sendMessage(embed.build()).queue();
        }
    }
}