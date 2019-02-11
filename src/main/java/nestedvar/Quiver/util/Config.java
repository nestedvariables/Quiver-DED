package nestedvar.Quiver.util;

import java.io.File;
import java.io.FileInputStream;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.yaml.snakeyaml.Yaml;

public class Config {
    public void load() {
        // TODO LOAD SAVE CREATE YAML
        /*
        try {
            Properties properties = new Properties();
            InputStream input = new FileInputStream("quiver.properties");
            properties.load(input);
        }
        catch (FileNotFoundException e) {
            System.out.println("Configuration does not exist, creating one.");
            File configuration = new File("quiver.properties");
            try {
                Properties properties = new Properties();
                properties.setProperty("botname", "Quiver");
                properties.setProperty("shards", "-1");
                properties.setProperty("status", "ALPHA! Shard #%shard%");
                FileWriter writer = new FileWriter(configuration);
                properties.store(writer, "Configuration");
                writer.close();
            }
            catch (Exception ex) {
                System.exit(1);
            }
        }
        catch (Exception e) {
            System.exit(1);
        } */
    }

    /**
     * Convert YAML configuration 
     * into a JSONObject
     * @return JSONObject JSON format of YAML configuration
     */
    private JSONObject getConfig() {
        try {
            Yaml yaml = new Yaml();
            Object configuration = yaml.load(new FileInputStream(new File("quiver.yml")));
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(JSONValue.toJSONString(configuration));
            return json;
        }
        catch (Exception e) {
            new Logger(1, "Cannot load configuration (there might be an error in the file).");
            return null;
        } 
    }

    public String botName() {
        return getConfig().get("name").toString();
    }

    public String token() {
        return getConfig().get("token").toString();
    }

    public int shards() {
        return Integer.parseInt(getConfig().get("shards").toString());
    }
    
    public String status() {
        return getConfig().get("status").toString();
    }

    public String webhook() {
        return getConfig().get("webhook").toString();
    } 
}