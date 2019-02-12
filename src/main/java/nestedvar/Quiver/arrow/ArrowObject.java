package nestedvar.Quiver.arrow;

import java.util.ArrayList;

public class ArrowObject {
    public String name;
    public String description;
    public String author;
    public String version;

    Object[] listeners;

    public ArrowObject(String name, String description, String author, String version, ArrayList<Object> listeners) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.version = version;
        this.listeners = listeners.toArray();
        System.out.println("🏹 Arrows: Loaded " + name + " " + version + " by " + author + ".");
    }
}