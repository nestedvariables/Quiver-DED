package nestedvar.Quiver.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import nestedvar.Quiver.arrow.ArrowObject;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;

public class Logger {
    public Logger(int code, Exception error, Guild guild) {
        StringWriter sw = new StringWriter();
        error.printStackTrace(new PrintWriter(sw));
        String exception = sw.toString();

        EmbedBuilder fatal = new EmbedBuilder();
        fatal.setDescription(exception);
        guild.getTextChannelById("491459187620970505").sendMessage(fatal.build()).queue();
        System.out.println(exception);
    }

    public Logger(int code, Exception error) {
        StringWriter sw = new StringWriter();
        error.printStackTrace(new PrintWriter(sw));
        String exception = sw.toString();
        System.out.println(exception);
    }
 
    public Logger(int code, String error, Guild guild) {
        System.out.println(error);
    }

    public Logger(int code, String error) {
        System.out.println(error);
    }

    public Logger(int code, Exception error, ArrowObject arrow) {
        StringWriter sw = new StringWriter();
        error.printStackTrace(new PrintWriter(sw));
        String exception = sw.toString();
        System.out.println("[" + arrow.name + "] " + exception);
    }

    public Logger(int code, String error, ArrowObject arrow) {
        System.out.println("[" + arrow.name + "] " + error);
    }
}