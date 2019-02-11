package nestedvar.Quiver.events;

import java.awt.Color;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

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
            Webhooks webhook = new Webhooks(config.webhook());
            webhook.addEmbed(new Webhooks.EmbedObject()
                .setTitle("🏹 Quiver Shard Spawned")
                .setDescription("💥 Shard ID: **" + String.valueOf(event.getJDA().getShardInfo().getShardId()) + "**" +
                    "      📦 Guild Count: **" + String.valueOf(Quiver.shardManager.getGuilds().size()) + "**" +
                    "      👥 User Count: **" + String.valueOf(Quiver.shardManager.getUsers().size()) + "**")            
            );
            webhook.execute();
        }
        catch (Exception e) {
            new Logger(1, e);
        }
    }
}