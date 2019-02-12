package nestedvar.Quiver.arrow;

import java.util.ArrayList;

public class ArrowBuilder {
    private String name;
    private String description;
    private String author;
    private String version;
    private ArrayList<Object> listeners = new ArrayList<Object>();

    /**
     * Create a new Arrow
     * @param name
     * @param description
     * @param author
     * @param version
     */
    public ArrowBuilder(String name, String description, String author, String version) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.version = version;
    }

    /**
     * Registers an external listener internally
     * @param listener Instance of listener
     */
    public void addListener(Object... listener) {
        listeners.add(listener);
    }

    public void build() {
        ArrowObject arrow = new ArrowObject(name, description, author, version, listeners);
        ArrowHandler.arrows.add(arrow);
    }
}