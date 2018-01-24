package algorithms.chapter2;

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

    public static void sort(int[] a, int lo, int hi) {
        if (hi - lo <= 0) {
            return;
        }
        if (hi - lo == 1) {
            if (a[hi] > a[lo]) {
                swap(a, hi, lo);
            }
            return;
        }
        int j = lo;
        int lp = lo + 1;
        int hp = hi;
        int p = lo;
        while (lp <  hp) {
            while (a[lp] <= a[p]) lp++;
            while (a[hi] >= a[p]) hi--;
            swap(a, lp, hi);
        }

        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
        }

        public static void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
}
