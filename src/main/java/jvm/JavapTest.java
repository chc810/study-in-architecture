package jvm;

/**
 * <dl>
 * <dt>JavapTest</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/11/27</dd>
 * </dl>
 *
 * @author cuihc
 */
public class JavapTest {

    private static final int _P_1 = 1;
    public static final int _P_2 = 2;

    public static void main(String[] args) {
        int m = 0, n = 0;
        for (int i = 0; i < 10; i++) {
            m = m++;
            n = ++n;
        }
        System.out.println("m = " + m);
        System.out.println("n = " + n);
    }
}
