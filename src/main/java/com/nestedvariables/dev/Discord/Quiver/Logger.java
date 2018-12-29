package com.nestedvariables.dev.Discord.Quiver;

public class Logger {

    // Logger method
    public static void log(Exception e) {
        if (Info.debug) {
            e.printStackTrace();
        }
    }
}