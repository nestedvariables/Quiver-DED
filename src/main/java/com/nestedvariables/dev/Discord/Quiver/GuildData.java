package com.nestedvariables.dev.Discord.Quiver;

import java.util.Random;

public class GuildData {

    static Random random = new Random();
    
    // Return embed color 
    public static Integer embedColor() {
        return random.nextInt(0xffffff + 1);
    }
    
    // Return default locale
    public static String locale(String region) {
        switch (region) {
            case "us-east":
                return "en_US";
            case "us-west":
                return "en_US";
            case "us-central":
                return "en_US";
            case "us-south":
                return "en_US";
            case "brazil":
                return "en_US";
            case "eu-central":
                return "en_UK";
            case "hongkong":
                return "en_US";
            case "japan":
                return "en_US";
            case "russia":
                return "ru_RU";
            case "singapore":
                return "en_US";
            case "southafrica":
                return "en_UK";
            case "sydney":
                return "en_UK";
            case "eu-west":
                return "en_UK";
            default:
                return null;
        }
    }
}