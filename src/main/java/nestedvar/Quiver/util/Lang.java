package nestedvar.Quiver.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import nestedvar.Quiver.Quiver;
import net.dv8tion.jda.core.entities.Guild;

public class Lang {
    private String[] locales = {
        "en_US",
        "es_SP",
        "he_IL",
        "ru_RU"
    };

    /**
     * Load locales. Save locale files if
     * they don't already exist.
     */
    public void load() {
        File file = new File("locale");
        if (file.isDirectory()) {
            try {
                for (String locale : locales) {
                    File verify = new File("locale/" + locale + ".json");
                    if (!verify.isFile()) {
                        InputStream inputStream = new URL("https://raw.githubusercontent.com/NestedVariables/Quiver/master/src/main/java/nestedvar/Quiver/locale/" + locale + ".json").openStream();  
                        Files.copy(inputStream, Paths.get("locale/" + locale + ".json"), StandardCopyOption.REPLACE_EXISTING);  
                        System.out.println("📂 Regenerated locale '" + locale + "'.");
                    }
                }
            }
            catch (Exception e) {
                new Logger(1, e);
            }
        }
        else {    
            try {
                File dir = new File("locale");
                dir.mkdir();
                for (String locale : locales) {
                    InputStream inputStream = new URL("https://raw.githubusercontent.com/NestedVariables/Quiver/master/src/main/java/nestedvar/Quiver/locale/" + locale + ".json").openStream();  
                    Files.copy(inputStream, Paths.get("locale/" + locale + ".json"), StandardCopyOption.REPLACE_EXISTING);  
                }
                System.out.println("📂 Downloaded locale files.");
            }
            catch (Exception e) {
                new Logger(1, e);
            }
        } 
    }

    // Returns message with proper locale for guild
    public String getMessage(Guild guild, String message) {
        JSONParser parser = new JSONParser();
        Data data = new Data();
        try {
            File localeFile = new File("locale/" + data.getLocale(guild) + ".json");
            InputStream in = new FileInputStream(localeFile);
            Object object = parser.parse(IOUtils.toString(in, "UTF-8"));
            JSONObject json = (JSONObject) object;
            in.close();
            return (String) json.get(message);
        } 
        catch (Exception e) {
            try {
                InputStream in = Quiver.class.getResourceAsStream("locale/" + data.getLocale(guild) + ".json");
                Object object = parser.parse(IOUtils.toString(in, "UTF-8"));
                JSONObject json = (JSONObject) object;
                in.close();

                new Logger(2, "Locale files for '" + data.getLocale(guild) + "' are missing. Using backup locales.", guild);

                return json.get(message).toString();
            }
            catch (Exception ex) {
                new Logger(1, ex, guild);
                ex.printStackTrace();
                return "{missing locale}";
            }
        }
    }
}