package nestedvar.Quiver.arrow;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import nestedvar.Quiver.Quiver;
import nestedvar.Quiver.util.Logger;

public class ArrowHandler {
    public static URLClassLoader loader;
    public static ArrayList<String> arrows = new ArrayList<String>();
    public static HashMap<String, HashMap<String, String>> arrowInfo = new HashMap<String, HashMap<String, String>>();
    public static ArrayList<Object> arrowClasses = new ArrayList<Object>();
    public static ArrayList<Object[]> listeners = new ArrayList<Object[]>();

    /**
     * Checks if the Arrows 
     * folder is empty
     */
    public void createFolder() {
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

                while (entries.hasMoreElements()) {
                    try {
                        JarEntry jarEntry = entries.nextElement();
                        if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")) {
                            continue;
                        }
                        String className = jarEntry.getName().substring(0, jarEntry.getName().length() - 6);
                        className = className.replace('/', '.');
                        Class<?> c = loader.loadClass(className);
                        Object classObj = c.newInstance();
                        // TODO figure this crap out
                        Map<String,String> testmap = new HashMap<String,String>();
                        testmap.put("test", "test");
                    

                            Arrow arrow = (Arrow) classObj;
                            arrow.load();
                        

                        //Method method = c.getMethod("onLoad");



                        // Add class to external class list (for unloading)
                        arrowClasses.add(classObj);
                        //method.invoke(classObj);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                }
                jar.close();
            }
            catch (Exception e) {
                new Logger(1, e);
            }
        }
    }

    /**
     * Unloads all loaded Arrows
     */
    public void unload() {
        try {
            loader.close();
            for (Object[] obj : listeners) {
                Quiver.builder.removeEventListeners(obj);
            }
        }
        catch (Exception e) {
            new Logger(1, e);
        }
    }

    /** 
     * Reloads the arrows that are currently 
     * loaded and loads new arrows
     */
    public void reload() {
        unload();
        load();
    }
}