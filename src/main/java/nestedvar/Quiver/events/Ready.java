package nestedvar.Quiver.events;

import nestedvar.Quiver.Quiver;
import nestedvar.Quiver.util.Config;
import nestedvar.Quiver.util.Logger;
import nestedvar.Quiver.util.Webhooks;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Ready extends ListenerAdapter {
    public void onReady(ReadyEvent event) {
        try {
            Config config = new Config();
            Webhooks webhook = new Webhooks(config.get("webhookURL"));
            webhook.addEmbed(new Webhooks.EmbedObject()
                .setTitle(":bow_and_arrow: Quiver Shard Spawned")
                .setDescription(":boom: Shard ID: **" + String.valueOf(event.getJDA().getShardInfo().getShardId()) + "**" +
                    "      :package: Guild Count: **" + String.valueOf(Quiver.shardManager.getGuilds().size()) + "**" +
                    "      :busts_in_silhouette: User Count: **" + String.valueOf(Quiver.shardManager.getUsers().size()) + "**")            
            );
            webhook.execute();
        }
        catch (Exception e) {
            new Logger(1, e);
        }
    }
}