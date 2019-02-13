package nestedvar.Quiver.events;

import java.io.File;
import java.util.List;

import net.dv8tion.jda.core.entities.Message.Attachment;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class GuildMessageReceived extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        // Custom Arrow uploading feature
        List<Attachment> attachments = event.getMessage().getAttachments();

        if (attachments.size() > 0) {
            for (Attachment attachment : attachments) {
                if (attachment.getFileName().endsWith(".jar")) {
                    System.out.println("uploaded a jar");
                    
                    Thread thread = new Thread() {
                    @Override
                        public void run() {
                            File file = new File("temp/" + attachment.getFileName());
                            attachment.download(file);
                            event.getChannel().sendMessage("Uploaded").queue();
                        }
                    };
                    thread.run();
                }
            }
        }
    }
}