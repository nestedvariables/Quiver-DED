package nestedvar.Quiver.util;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Config {
    public static JSONObject json;    

    /**
     * Load JSON configuration into memory
     * @return JSONObject
     */
    public Config() {
        try {
            JSONParser parser = new JSONParser();
            json = (JSONObject) parser.parse(new FileReader("quiver.json"));
            System.out.println(json.toString());
        }
        catch (Exception e) {new Logger(1, "Cannot load configuration (there might be an error in the file).");} 
    }

    /**
     * Return the value of a key from configuration
     * @param key
     * @return String value
     */
    public String get(String key) {
        return json.get(key).toString();
    }
}