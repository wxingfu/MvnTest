package com.weixf.func;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class DynamicLoaderClass extends ClassLoader {

    private final String path;

    public DynamicLoaderClass() {
        this(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath());
    }

    public DynamicLoaderClass(String path) {
        this.path = path;
    }

    @Override
    public Class<?> loadClass(String className) throws ClassNotFoundException {
        Class<?> cls = loadClassByParent(className);
        if (cls == null) {
            cls = loadClassBySelf(className);
        }
        return cls;
    }

    private Class<?> loadClassBySelf(String className) throws ClassNotFoundException {
        return findClass(className);
    }

    private Class<?> loadClassByParent(String className) {
        Class<?> cls = null;
        try {
            cls = super.loadClass(className);
        } catch (ClassNotFoundException e) {
            // load class failed;
        }
        return cls;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        byte[] b = getResourceForCurrentPath(className);
        return defineClass(className, b, 0, b.length);
    }

    private byte[] getResourceForCurrentPath(String className) throws ClassNotFoundException {
        return getResourceForPath(generateResourcePath(className));
    }

    private byte[] getResourceForPath(String resourcePath) throws ClassNotFoundException {
        checkResourceIsExist(resourcePath);
        return readResource(resourcePath);
    }

    private byte[] readResource(String resourcePath) throws ClassNotFoundException {
        byte[] result = null;
        File file = new File(resourcePath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            result = new byte[fis.available()];
            int read = fis.read(result);
            fis.close();
        } catch (FileNotFoundException e) {
            // This should only happen with multithreading
        } catch (IOException e) {
            throw new ClassNotFoundException();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    private void checkResourceIsExist(String resourcePath) throws ClassNotFoundException {
        File file = new File(resourcePath);
        if (!file.exists())
            throw new ClassNotFoundException();
    }

    private String generateResourcePath(String className) {
        return path + "/" + className.replace(".", "/") + ".class";
    }

}