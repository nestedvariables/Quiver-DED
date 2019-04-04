package nestedvar.Quiver.arrow;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.sun.tools.attach.VirtualMachine;

import nestedvar.Quiver.arrow.loader.ArrowLoader;

public class ArrowHandler {
    // TODO remove
    public static ArrayList<ArrowObject> arrows = new ArrayList<ArrowObject>();

    /**
     * Creates the "arrows" folder if it doesn't exist
     */
    private void createDirectory() {
        File f = new File("arrows");
        if (f.isDirectory())
            return;
        f.mkdir();
    }

    /**
     * Load all plugins in Arrows folder
     * 
     * @throws IOException
     */
    public void load() {
        try {
            File f = new File("arrows/DependsTest.jar");
            long pid = ProcessHandle.current().pid();
            VirtualMachine jvm = VirtualMachine.attach(String.valueOf(pid));
            jvm.loadAgent(f.getAbsolutePath());
            jvm.detach();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
   /* public void load(final URI dir) throws IOException {
        final ArrayList<Class<?>> arrows = new ArrayList<>();
        final Path path = Paths.get(dir);
        if (!Files.exists(path))
            createDirectory();
        if (!Files.isDirectory(path))
            throw new IllegalArgumentException(dir + " is not a directory");
        final Map<Path, URL> jars = new HashMap<>();
        Files.newDirectoryStream(path).forEach(f -> {
            try {
                if (f.getFileName().toString().endsWith(".jar"))
                    jars.put(f, f.toUri().toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
        final ArrowLoader loader = new ArrowLoader(jars.values().toArray(new URL[] {}));
        jars.keySet().forEach(p -> {
            try {
                final File f = p.toAbsolutePath().toFile();
                final JarFile j = new JarFile(f);
                Enumeration<JarEntry> e = j.entries();
                while (e.hasMoreElements()) {
                    final JarEntry je = e.nextElement();
                    if (je.isDirectory() || !je.getName().endsWith(".class"))
                        continue;
                    String c = je.getName().substring(0, je.getName().length() - 6);
                    c = c.replace("/", ".");
                    Class<?> cl = Class.forName(c, true, loader);
                    arrows.add(cl);
                }
                j.close();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        });
    }*/

    /**
     * Unload all plugins in Arrows folder
     */
    public void unload() {
        
    }
}