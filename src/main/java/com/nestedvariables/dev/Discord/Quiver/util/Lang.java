package com.nestedvariables.dev.Discord.Quiver.util;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import net.dv8tion.jda.core.entities.Guild;

public class Lang {
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