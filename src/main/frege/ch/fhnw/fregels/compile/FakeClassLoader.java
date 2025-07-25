package ch.fhnw.fregels.compile;

import java.net.URL;
import java.net.URLClassLoader;

public class FakeClassLoader extends URLClassLoader {

    public FakeClassLoader() {
        super(new URL[0]);
    }

    /**
     * Overrides loadClass to do nothing and always return Object.class.
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return Object.class;
    }


    /** Inspired by the way java.Lang.Runnable is built. This allows us to
     * provide a custom class loader implementation and tell Frege that it is
     * actually a `URLClassLoader` */
    public static java.net.URLClassLoader classLoader() {
        return new FakeClassLoader();
    }
}
