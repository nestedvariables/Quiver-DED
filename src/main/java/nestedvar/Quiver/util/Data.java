package nestedvar.Quiver.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import net.dv8tion.jda.core.entities.Guild;

public class Data {
    public static HashMap<String, HashMap<String, String>> settings = new HashMap<String, HashMap<String, String>>();

    //Loads existing guild settings from database
    public void load() {
        try {
            Connection connection = SQL.getConn();
            Statement statement = connection.createStatement();
            ResultSet r = statement.executeQuery("SELECT * FROM `guild_options`");
                   
            if (!r.next()) {
                Logger logger = new Logger();
                logger.log(2, "No guilds in database.");
            }
            else {
                while (r.next()) {
                    HashMap<String, String> data = new HashMap<String, String>();
                    data.put("locale", r.getString("locale"));
                    data.put("prefix", r.getString("prefix"));
                    // TODO log channel and stuff
                    settings.put(r.getString("guild"), data);
                }
            }
            statement.close();
        }
        catch (Exception e) {
            Logger logger = new Logger();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            logger.log(1, String.valueOf(e));
        }
    }

    // Return prefix for guild
    public String getPrefix(Guild guild) {
        HashMap<String, String> map = settings.get(guild.getId());
        return map.get("prefix");
    }

    // Set prefix for guild
    public void setPrefix(Guild guild, String prefix) {
        try {
            Connection connection = SQL.getConn();
            Statement statement = connection.createStatement();
            statement.execute("UPDATE `guild_options` SET `prefix`='" + prefix + "' WHERE `guild`='" + guild.getId() + "'");
            HashMap<String, String> map = settings.get(guild.getId());
            map.put("prefix", prefix);
            settings.put(guild.getId(), map);
            statement.close();
        }
        catch (Exception e) {
            Logger logger = new Logger();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            logger.log(1, String.valueOf(e));
        }
    }

    // Return locale for guild
    public String getLocale(Guild guild) {
        HashMap<String, String> map = settings.get(guild.getId());
        return map.get("locale");
    }

    // Return default locale
    public String getDefaultLocale(Guild guild) {
        if (guild.getRegionRaw().equals("us-east")) return "en_US";
        else if (guild.getRegionRaw().equals("us-west")) return "en_US";
        else if (guild.getRegionRaw().equals("us-central")) return "en_US";
        else if (guild.getRegionRaw().equals("us-south")) return "en_US";
        else if (guild.getRegionRaw().equals("brazil")) return "pt_BR";
        else if (guild.getRegionRaw().equals("eu-central")) return "en_GB";
        else if (guild.getRegionRaw().equals("hongkong")) return "en_HK";
        else if (guild.getRegionRaw().equals("japan")) return "ja_JP";
        else if (guild.getRegionRaw().equals("russia")) return "ru_RU";
        else if (guild.getRegionRaw().equals("singapore")) return "en_SG";
        else if (guild.getRegionRaw().equals("southafrica")) return "en_ZA";
        else if (guild.getRegionRaw().equals("sydney")) return "en_AU";
        else if (guild.getRegionRaw().equals("eu-west")) return "en_GB";
        else return "en_US";
    }

    // Set locale for guild
    public void setLocale(Guild guild, String locale) {
        try {
            Connection connection = SQL.getConn();
            Statement statement = connection.createStatement();
            statement.execute("UPDATE `guild_options` SET `locale`='" + locale + "' WHERE `guild`='" + guild.getId() + "'");
            HashMap<String, String> map = settings.get(guild.getId());
            map.put("locale", locale);
            settings.put(guild.getId(), map);
            statement.close();
        }
        catch (Exception e) {
            Logger logger = new Logger();
            logger.log(1, String.valueOf(e), guild);
        }
    }
}