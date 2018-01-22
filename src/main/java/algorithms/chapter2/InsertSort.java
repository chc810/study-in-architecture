package algorithms.chapter2;

import org.junit.Test;

public class InsertSort {

    public static void doSort(int[] a) {
        for (int i=0;i<a.length - 1;i++) {
            for (int j = i+1;j>0 && a[j] < a[j - 1];j--) {
                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }

        }
    }

    /**
     * 为归并排序准备的
     * @param a
     */
    public static void doSort4Merge(int[] a, int lo, int hi) {
        for (int i=lo;i<hi;i++) {
            for (int j = i+1;j>lo && a[j] < a[j - 1];j--) {
                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }

        }
    }

    @Test
    public void test() {
        int[] a=new int[]{34,6,2,5,7,4,1,1};
        doSort(a);
        for (int i : a) {
            System.out.print(i + " ");
        }
    }

    @Test
    public void test1() {
        int[] a=new int[]{34,6,2,5,7,4,1,1};
        doSort4Merge(a,1,6);
        for (int i : a) {
            System.out.print(i + " ");
        }
    }
}
