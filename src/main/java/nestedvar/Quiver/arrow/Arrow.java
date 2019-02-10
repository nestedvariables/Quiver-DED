package nestedvar.Quiver.arrow;

import java.util.ArrayList;

import nestedvar.Quiver.Quiver;
import nestedvar.Quiver.util.Logger;

public class Arrow {  
    /**
     * Sets the name of the Arrow 
     * (displayed on arrows list)
     * @param name Name of Arrow
     */
    public void setName(String name) {

    }
    
    /**
     * Sets a description
     * @param description Description for Arrow
     */
    public void setDescription(String description) {

    }

    /** 
     * Sets the author of the Arrow
     * @param author Name of author
     */
    public void setAuthor(String author) {
 
    }

    /**
     * Sets the version of the Arrow
     * @param version Version of Arrow
     */
    public void setVersion(String version) {

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
            Logger logger = new Logger();
            logger.log(1, String.valueOf(e));
        }
    }
}