package jvm;

import java.io.*;

public class FileClassLoader extends ClassLoader{

    private String rootDir;

    public FileClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
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
        FileClassLoader fileClassLoader = new FileClassLoader("C:\\Users\\cuihc\\Desktop");
        Class clazz = fileClassLoader.loadClass("jvm.ServiceTestImpl");
        System.out.println(clazz.getClassLoader());
        Class clazz1 = fileClassLoader.loadClass("jvm.ServiceTestImpl");
        System.out.println(clazz1.getClassLoader());
        ((ServiceTest)clazz.newInstance()).say();
    }
}
