package org.java.week0;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author zhangwei
 */
public class MyClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Object obj = new MyClassLoader().findClass("Hello").newInstance();
            obj.getClass().getMethod("hello").invoke(obj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) {
        String path = "F:\\Hello.xlass";
        try {
            byte[] bytes = file2byteArray(path);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] file2byteArray(String path) throws IOException {
        try(InputStream is = new FileInputStream(new File(path));
            ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int b;
            while ((b = is.read(buffer)) != -1) {
                os.write(buffer, 0, b);
            }
            return os.toByteArray();
        }
    }
}
