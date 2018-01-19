package algorithms.chapter1;

import org.junit.Test;

import java.io.File;

/**
 * <dl>
 * <dt>E1_3_43</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/1/9</dd>
 * </dl>
 *
 * @author cuihc
 */
public class E1_3_43 {

    @Test
    public void main() {
        File file = new File("D:\\IdeaProjects\\study\\study-in-architecture\\src");
        accessFile(file,0);
    }

    public void accessFile(File file, int n) {
        System.out.println(getH(n) + file.getName());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i=0;i<files.length;i++) {
                accessFile(files[i],n+1);
            }
        } else {
            return;
        }
    }

    private String getH(int num) {
        String s = "";
        for (int i=0;i<num;i++) {
                s+= "---";
        }
       return s;
    }
}
