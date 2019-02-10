package nestedvar.Quiver.arrow;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import nestedvar.Quiver.util.Logger;

public class ArrowHandler {
    // Check if arrows folder is empty
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
                Logger logger = new Logger();
                logger.log(1, String.valueOf(e));
            }
        }
    }

    // Load all arrows
    public void load() {
        File dir = new File("arrows");
        File[] files = dir.listFiles((d, file) -> file.endsWith(".jar"));

        for (File file : files) {
            try {
                URL[] urls = { 
                    file.toURI().toURL()
                };
                URLClassLoader loader = URLClassLoader.newInstance(urls);
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
                        Method method = c.getMethod("onLoad");
                        Object dog = c.newInstance();
                        method.invoke(dog);
                    }
                    catch (Exception e) {
                        continue;
                    }
                }
                jar.close();
            }
            catch (Exception e) {
                Logger logger = new Logger();
                logger.log(1, String.valueOf(e));
            }
        }
    }

    /** 
     * Reloads the arrows that
     * are currently loaded and 
     * loads new arrows
     */
    public void reload() {
        // Load arrows again
        load();
    }
}