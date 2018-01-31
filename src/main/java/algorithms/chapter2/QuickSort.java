package algorithms.chapter2;

import org.junit.Test;

/**
 * <dl>
 * <dt>QuickSort</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/1/24</dd>
 * </dl>
 *
 * @author cuihc
 */
public class QuickSort {


    @Test
    public void test() {
        int[] a = new int[]{3,6,3,3,3};
        sort(a,0,a.length - 1);
        for (int aa : a) {
            System.out.print(aa + " ");
        }
    }

    public static void sort(int[] a, int lo, int hi) {
        if (hi - lo <= 0) {
            return;
        }
        if (hi - lo == 1) {
            if (a[hi] < a[lo]) {
                swap(a, hi, lo);
            }
            return;
        }
        int j = partion(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
        }

    public static int partion(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (a[++i] < a[lo]) {
                if (i == hi) {
                    break;
                }
            }
            while (a[--j] > a[lo]) {
            }
            if (i>=j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, lo,j);
        return j;
    }


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
