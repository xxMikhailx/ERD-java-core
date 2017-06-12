package com.epam.classloaders.main;

import com.epam.classloaders.classloader.CustomClassLoader;
import java.lang.reflect.Method;

@SuppressWarnings("unchecked")
public class Main {

    public static void main(String args[]) throws Exception {
        String progClass = args[0];
        String progArgs[] = new String[args.length - 1];
        System.arraycopy(args, 1, progArgs, 0, progArgs.length);

        CustomClassLoader ccl = new CustomClassLoader();
        Class clas = ccl.loadClass(progClass);
        Class mainArgType[] = { (new String[0]).getClass() };
        Method main = clas.getMethod("main", mainArgType);
        Object argsArray[] = { progArgs };
        main.invoke(null, argsArray);
    }

}