package nestedvar.Quiver.arrow.loader;

import java.net.URL;
import java.net.URLClassLoader;

public class ArrowLoader extends URLClassLoader {

    /*public ArrowLoader(URL jarfile) {
        super(new URL[]{jarfile});
    }*/

    public ArrowLoader(URL[] jars) {
        super(jars);
    }
}