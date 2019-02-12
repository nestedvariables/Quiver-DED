package nestedvar.Quiver.arrow;

import java.util.List;

import nestedvar.Quiver.util.Logger;

public class ArrowObject {
    public String name;
    public String description;
    public String author;
    public String version;

    public Object[] listeners;

    /**
     * Create a new Arrow object
     * @param name
     * @param description
     * @param author
     * @param version
     */
    public ArrowObject(String name, String description, String author, String version, List<Object> listeners) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.version = version;
        this.listeners = listeners.toArray(new Object[listeners.size()]);
        System.out.println("🏹 Arrows: Loaded " + name + " " + version + " by " + author + ".");
    }

    public void log(int type, Exception e) {
        new Logger(type, e, this);
    }

    public void log(int type, String error) {
        new Logger(type, error, this);
    }
}