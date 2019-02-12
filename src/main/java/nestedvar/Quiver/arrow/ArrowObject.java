package nestedvar.Quiver.arrow;

import java.util.List;

public class ArrowObject {
    public String name;
    public String description;
    public String author;
    public String version;

    public Object[] listeners;

    public ArrowObject(String name, String description, String author, String version, List<Object> listeners) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.version = version;
        this.listeners = listeners.toArray(new Object[listeners.size()]);
        System.out.println("🏹 Arrows: Loaded " + name + " " + version + " by " + author + ".");
    }
}