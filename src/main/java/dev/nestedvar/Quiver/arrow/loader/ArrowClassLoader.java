package dev.nestedvar.Quiver.arrow.loader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ArrowClassLoader extends URLClassLoader {
    private static final Set<ArrowClassLoader> loaders = new CopyOnWriteArraySet<>();

    static {
        ClassLoader.registerAsParallelCapable();
    }

    public ArrowClassLoader(final URL[] urls) {
        super(urls);
    }

    public void addClassloaders() {
        loaders.add(this);
    }

    void addPath(final Path path) {
        try {
            addURL(path.toUri().toURL());
        } catch (MalformedURLException exception) {
            throw new AssertionError(exception);
        }
    }

    @Override
    public void close() throws IOException {
        loaders.remove(this);
        super.close();
    }

    @Override
    protected Class<?> loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
        return loadClass // TODO
    }

    private Class<?> loadClass0(final String name, final boolean reso) {

    }
}
