package jvm;

import java.io.*;

public class FileClassLoader2 extends ClassLoader{

    private String rootDir;

    public FileClassLoader2(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassDate(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        }
        return defineClass(name, classData, 0, classData.length);
    }

    private byte[] getClassDate(String name) {
        String path = classNameToPath(name);
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            // 读取类文件的字节码
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String name) {
        return rootDir + File.separator + name.replace(".", File.separator) + ".class";
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        FileClassLoader2 fileClassLoader = new FileClassLoader2("D:\\IdeaProjects\\study\\study-in-architecture\\target\\classes");
        Class clazz = fileClassLoader.loadClass("jvm.ServiceTestImpl");
//        System.out.println(clazz.getClassLoader());
//        ((ServiceTest)clazz.newInstance()).say();
        System.out.println(fileClassLoader.getClass().getClassLoader());
        System.out.println("自定义类加载器的父加载器:" + fileClassLoader.getParent());
        System.out.println("系统默认的AppClassLoader: "+ClassLoader.getSystemClassLoader());
        System.out.println("AppClassLoader的父类加载器: "+ClassLoader.getSystemClassLoader().getParent());
        System.out.println("ExtClassLoader的父类加载器: "+ClassLoader.getSystemClassLoader().getParent().getParent());
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}
