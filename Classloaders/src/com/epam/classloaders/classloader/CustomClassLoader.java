package com.epam.classloaders.classloader;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class CustomClassLoader extends ClassLoader {

    private HashMap<String,Class> cache = new HashMap<>();

    public CustomClassLoader() {
        super(CustomClassLoader.class.getClassLoader());
    }

    @Override
    public Class loadClass(String name) throws ClassNotFoundException {
        System.out.println("[CLASSLOADER] Loading Class '" + name + "'");
        if (name.startsWith("com.epam")) {
            System.out.println("[CLASSLOADER] Loading Class using CustomClassLoader");
            return findClass(name);
        }
        return super.loadClass(name);
    }

    protected Class findClass(String name) throws ClassNotFoundException {
        if (cache.containsKey(name)){
            return cache.get(name);
        }
        byte[] b = null;
        try {
            b = loadClassFileData(name);
            Class c = defineClass(name, b, 0, b.length);
            resolveClass(c);
            cache.put(name, c);
            System.out.println("[CLASSLOADER] Class " + name + " loaded by " + c.getClassLoader());
            return c;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] loadClassFileData(String name) throws IOException {
        String file = name.replace('.', File.separatorChar) + ".class";
        System.out.println();
        InputStream stream = getClass().getClassLoader().getResourceAsStream(
                file);
        int size = stream.available();
        byte buff[] = new byte[size];
        DataInputStream in = new DataInputStream(stream);
        in.readFully(buff);
        in.close();
        return buff;
    }
}