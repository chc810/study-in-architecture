package algorithms.chapter2;

import org.junit.Test;

/**
 * <dl>
 * <dt>MergeSort</dt>
 * <dd>Description:归并排序</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/1/19</dd>
 * </dl>
 *
 * @author cuihc
 */
public class MergeSort {

    @Test
    public void doSort() {
        int[] a = new int[]{7,2};
        int[] aux = new int[a.length];
        merge(a, aux, 0, (a.length - 1) / 2, a.length - 1);
        for (int aa : a) {
            System.out.print(aa + " ");
        }
    }

    private void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        if (lo == hi) {
            return;
        }
        if (lo+1 == hi) {
            if (a[lo] > a[hi]) {
                int temp = a[lo];
                a[lo] = a[hi];
                a[hi] = temp;
            }
            return;
        }
        merge(a, aux, lo, (lo + mid) / 2, mid);
        merge(a, aux, mid + 1, (mid + 1 + hi) / 2, hi);
        for (int i=lo;i<=hi;i++) {
            aux[i] = a[i];
        }
        int i = lo,j=mid + 1;
        for (int k = lo;k<=hi;) {
            if (i >= mid +1)  a[k++] = aux[j++];
            else if (j >=hi + 1) a[k++] = aux[i++];
            else if (aux[i] > aux[j]) a[k++] = aux[j++];
            else a[k++] = aux[i++];
        }
    }
}
