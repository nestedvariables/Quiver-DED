package com.nestedvariables.dev.Discord.Quiver;

public class Logger {

    // Logger method
    public static void log(String info) {
        if (Info.debug) {
            System.out.println(info);
        }
    }
}