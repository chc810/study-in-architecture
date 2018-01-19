package file;

public class DuplicateTest {

  /*  public static void main(String[] args) {
//        checkDuplicate(MyTest.class);
//        checkDuplicate("META-INF/MANIFEST.MF");
        checkDuplicate("config.properties");
    }

    public static void checkDuplicate(Class cls) {
        checkDuplicate(cls.getName().replace('.', '/') + ".class");
    }

    public static void checkDuplicate(String path) {
        try {
            // 在ClassPath搜文件
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(path);
            Set files = new HashSet();
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String file = url.getFile();
                    openFile(url);
                    if (file != null && file.length() > 0) {
                        files.add(file);
                    }
                }
            }
            // 如果有多个，就表示重复
            if (files.size() > 1) {
                System.out.println("Duplicate class " + path + " in " + files.size() + " jar " + files);
            }
            System.out.println("number = " + files.size());
        } catch (Throwable e) { // 防御性容错
            System.out.println(e.getMessage());
        }
    }

    public static void openFile(URL url) throws IOException{
        BufferedReader br =  new BufferedReader(new InputStreamReader(url.openStream()));
        System.out.println(br.readLine());
        br.close();
    }*/
}
