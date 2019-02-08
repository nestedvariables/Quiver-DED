package com.nestedvariables.dev.Discord.Quiver;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.google.gson.JsonParser;
import com.nestedvariables.dev.Discord.Quiver.util.Embeds;
import com.nestedvariables.dev.Discord.Quiver.util.Lang;
import com.nestedvariables.dev.Discord.Quiver.util.Logger;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Codeblocks extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] lines = event.getMessage().getContentRaw().split("\n");
        
        if (event.getMessage().getContentRaw().startsWith("```") && event.getMessage().getContentRaw().endsWith("```") && lines.length > 10) {
            Lang lang = new Lang();
            Embeds embed = new Embeds();
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost("https://hastebin.com/documents");
            
            String content = "";
            for (int i = 1; i < (lines.length - 1); i++) {
                content = content + lines[i] + "\n";
            }
            
	        try {
		        post.setEntity(new StringEntity(content));
                HttpResponse response = client.execute(post);
                String result = EntityUtils.toString(response.getEntity());
                
                event.getMessage().delete().queue();

                JsonParser parser = new JsonParser();

                EmbedBuilder codeblock = new EmbedBuilder();
                codeblock.setColor(embed.color());
                codeblock.setTitle(lang.getMessage(event.getGuild(), "codeBlockEmbedTitle"));
                codeblock.setDescription(lang.getMessage(event.getGuild(), "codeBlockEmbedDescription").replace("{link}", "https://hastebin.com/" + parser.parse(result).getAsJsonObject().get("key").getAsString()));
                codeblock.setFooter(lang.getMessage(event.getGuild(), "name") + " " + lang.getMessage(event.getGuild(), "codeBlockEmbedFooter"), event.getJDA().getSelfUser().getAvatarUrl());
                event.getChannel().sendMessage(codeblock.build()).queue();
                codeblock.clear();

                content = "";
                result = "";
            } 
            catch (Exception e) {
                Logger logger = new Logger();
                logger.log(1, e.toString(), event.getGuild());
            }
        }
    }
}