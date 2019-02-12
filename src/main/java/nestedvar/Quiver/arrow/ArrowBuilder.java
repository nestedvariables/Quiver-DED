package nestedvar.Quiver.arrow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrowBuilder {
    private String name;
    private String description;
    private String author;
    private String version;
    private List<Object> listeners = new ArrayList<Object>();

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
        Collections.addAll(this.listeners, listener);
    }

    /**
     * Initializes arrow
     */
    public ArrowObject build() {
        ArrowObject arrow = new ArrowObject(name, description, author, version, listeners);
        ArrowHandler.arrows.add(arrow);
        return arrow;
    }
}