package nestedvar.Quiver.arrow;

public class ListenerTest implements Arrow {
    public void load() {
        System.out.println("a test");
    }

    public void unload() {
        System.out.println("another test");
    }
}