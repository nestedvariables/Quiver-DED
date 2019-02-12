package nestedvar.Quiver.arrow;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import nestedvar.Quiver.Quiver;
import nestedvar.Quiver.util.Logger;

public class ArrowHandler {
    public static URLClassLoader loader;
    public static ArrayList<ArrowObject> arrows = new ArrayList<ArrowObject>();

    // TODO MAKE THIS IDIOTS FUCK OFF
    public static HashMap<String, HashMap<String, String>> arrowArray = new HashMap<String, HashMap<String, String>>();
    public static HashMap<String, ArrayList<JarEntry>> classes = new HashMap<String, ArrayList<JarEntry>>();

    /**
     * Checks if the Arrows 
     * folder is empty
     */
    void createFolder() {
        File file = new File("arrows");
        if (file.isDirectory()) {
            if (file.list().length > 0) load();
        }
        else {    
            try {
                File arrowsDir = new File("arrows");
                arrowsDir.mkdir();
            }
            catch (Exception e) {
                new Logger(1, e);
            }
        }
    }

    /**
     * Loads all Arrows
     */
    public void load() {
        File dir = new File("arrows");
        File[] files = dir.listFiles((d, file) -> file.endsWith(".jar"));

        for (File file : files) {
            try {
                URL[] urls = { 
                    file.toURI().toURL()
                };
                loader = URLClassLoader.newInstance(urls);
                JarFile jar = new JarFile(file);
                Enumeration<JarEntry> entries = jar.entries();
                ArrayList<JarEntry> temp = new ArrayList<JarEntry>();

                while (entries.hasMoreElements()) {
                    try {
                        JarEntry jarEntry = entries.nextElement();

                        // Add class to JAR record
                        temp.add(jarEntry);
                        
                        if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")) {
                            continue;
                        }
                        String className = jarEntry.getName().substring(0, jarEntry.getName().length() - 6).replace('/', '.');
                        Class<?> clazz = loader.loadClass(className);
                        Object obj = clazz.newInstance();
                    
                        if (obj instanceof Arrow) {
                            Arrow arrow = (Arrow) obj;
                            arrow.load();
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                }
                classes.put(jar.getName(), temp);
                System.out.println(jar.getName().replace("arrows\\", "").replace(".jar", ""));
                jar.close();
            }
            catch (Exception e) {new Logger(1, e);}
        }
    }

    /**
     * Unloads all loaded Arrows
     */
    public void unload() {
        try {
            loader.close();
            /*for (Object obj : listeners) {
                Quiver.builder.removeEventListeners(obj);
            }*/
        }
        catch (Exception e) {
            new Logger(1, e);
        }
    }
}