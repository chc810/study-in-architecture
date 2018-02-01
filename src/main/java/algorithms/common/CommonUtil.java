package algorithms.common;

/**
 * <dl>
 * <dt>CommonUtil</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/1/31</dd>
 * </dl>
 *
 * @author cuihc
 */
public class CommonUtil {

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void syso(int[] a) {
        for (int aa : a) {
            System.out.print(aa + " ");
        }
    }
}
