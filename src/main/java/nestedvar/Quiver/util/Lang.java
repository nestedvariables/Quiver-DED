package nestedvar.Quiver.util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import net.dv8tion.jda.core.entities.Guild;

public class Lang {
    /**
     * Save locale files if
     * they don't already exist
     */
    public void load() {
        // TODO load locales from local folder
        /**
        File file = new File("locale");
        if (file.isDirectory()) {
            if (file.list().length > 0) return;
        }
        else {    
            try {
                File packageLocales = new File(Lang.class.getResource("locale/").getFile());
                File localeDir = new File("locale");
                localeDir.mkdir();
                FileUtils.copyDirectory(packageLocales, localeDir);
            }
            catch (Exception e) {
                Logger logger = new Logger();
                e.printStackTrace();
                logger.log(1, String.valueOf(e));
            }
        } */
    }

    // Returns message with proper locale for guild
    public String getMessage(Guild guild, String message) {
        JSONParser parser = new JSONParser();
        Data data = new Data();
        try {
            InputStream in = Lang.class.getResourceAsStream("locale/" + data.getLocale(guild) + ".json");
            Object object = parser.parse(IOUtils.toString(in, "UTF-8"));
            JSONObject json = (JSONObject) object;
            in.close();
            return (String) json.get(message);
        } 
        catch (Exception e) {
            Logger logger = new Logger();
            logger.log(1, String.valueOf(e), guild);
            return "";
        }
    }
}