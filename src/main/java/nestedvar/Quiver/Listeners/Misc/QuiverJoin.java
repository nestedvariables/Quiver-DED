package nestedvar.Quiver.Listeners.Misc;

import java.io.IOException;
import java.sql.SQLException;

import nestedvar.Quiver.Utilities.SQLDriver;
import nestedvar.Quiver.Utilities.SQLUtils;
import nestedvar.Quiver.Utilities.Webhooks;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class QuiverJoin extends ListenerAdapter{ 

    public void onGuildJoin(GuildJoinEvent event){
        SQLUtils sqlutils = new SQLUtils();
        SQLDriver sql = new SQLDriver();

        if(sqlutils.haveIBeenHere(event)) {
         try {
             Webhooks webhook = new Webhooks(System.getenv("QUIVERWEBHOOK"));
             webhook.addEmbed(new Webhooks.EmbedObject()
                .setTitle("⚠Discord has triggered a GuildJoinEvent for a Guild already in the database")
                .setDescription("Guild in question: " + event.getGuild().getName() + "\nGuild ID: " + event.getGuild().getId())
             );
             webhook.execute();
            }catch (IOException e){
             e.printStackTrace();
         }
        } else {
            try{
                Webhooks webhook = new Webhooks(System.getenv("QUIVERWEBHOOK"));
                webhook.addEmbed(new Webhooks.EmbedObject()
                    .setTitle("I've been added to a new guild!")
                    .setDescription("Guild in question: " + event.getGuild().getName() + "\nGuild ID: " + event.getGuild().getId())
                );
                webhook.execute();
                sql.getConn().createStatement().execute("INSERT INTO `guilds` VALUES('"+ event.getGuild().getId() + "', '" + event.getGuild().getName() + "', 'Q!', 'true', 'false')");
            } catch(SQLException | IOException e){
                e.printStackTrace();
            }
        }
    }
}
