
package nestedvar.Quiver.arrow;

import java.util.HashMap;

import nestedvar.Quiver.Quiver;
import nestedvar.Quiver.util.Logger;

public class Arrowold {  
    /**
     * Arrow constructor
     * Creates a new Arrow instance
     * @param name Name of Arrow
     * @param description A short description of the Arrow's functionality
     * @param author Name of Arrow's author
     * @param version Version of Arrow
     */
    public Arrowold(String name, String description, String author, String version) {
        HashMap<String, String> temp = new HashMap<String, String>();
        temp.put("description", description);
        temp.put("author", author);
        temp.put("version", version);
        ArrowHandler.arrows.add(name);
        ArrowHandler.arrowInfo.put(name, temp);
        new Logger(0, "🏹 Arrows > Loaded " + name + " " + version + " by " + author + ".");
    }

    /**
     * Adds listener to Quiver
     * @param listener Instance of class
     */
    public void addListener(Object... listener) {
        try {
            Quiver.builder.addEventListeners(listener);
            ArrowHandler.listeners.add(listener);

            

            new java.util.Timer().schedule( 
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Quiver.builder.removeEventListeners(listener);
                        System.out.println("removed listners");
                    }
                }, 
                15000 
            );
        }
        catch (Exception e) {
            new Logger(1, e);
        }
    }
}