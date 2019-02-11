package nestedvar.Quiver.arrow;

import nestedvar.Quiver.Quiver;
import nestedvar.Quiver.util.Logger;

public class Arrow {  
    /**
     * Arrow constructor
     * Creates a new Arrow instance
     * @param name Name of Arrow
     * @param description A short description of the Arrow's functionality
     * @param author Name of Arrow's author
     * @param version Version of Arrow
     */
    public Arrow(String name, String description, String author, String version) {
        System.out.println("🏹 Arrows: Loaded " + name + " " + version + " by " + author + ".");
    }

    /**
     * Adds listener to Quiver
     * @param listener Instance of class
     */
    public void addListener(Object... listener) {
        try {
            Quiver.builder.addEventListeners(listener);
        }
        catch (Exception e) {
            new Logger(1, e);
        }
    }
}