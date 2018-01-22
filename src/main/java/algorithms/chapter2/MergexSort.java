package algorithms.chapter2;

/**
 * 改进的归并排序
 *
 */
public class MergexSort {

    /**
     *1.当数组数量小于15时，使用插入排序
     * @param a
     * @param aux
     * @param lo
     * @param mid
     * @param hi
     */
    public static void merge1(int[] a, int[] aux, int lo, int mid, int hi) {
        if (hi - lo <= 15) {
            InsertSort.doSort4Merge(a, lo, hi);
            return;
        }
        merge1(a, aux, lo, (lo + mid) / 2, mid);
        merge1(a, aux, mid + 1, (mid + 1 + hi) / 2, hi);
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

    /**
     *
     * @param a
     * @param aux
     * @param lo
     * @param mid
     * @param hi
     */
    public static void merge2(int[] a, int[] aux, int lo, int mid, int hi) {
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
        merge2(a, aux, lo, (lo + mid) / 2, mid);
        merge2(a, aux, mid + 1, (mid + 1 + hi) / 2, hi);
        if (a[mid + 1] >= a[mid]) {
            return;
        }
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
