package algorithms.chapter2;

import org.junit.Test;

public class SelectSort {

    public static void doSort(int[] a) {
        for (int i=0;i<a.length;i++) {
            int minIdex = i;
            for (int j = i+1;j<a.length;j++) {
                if (a[j] < a[minIdex]) minIdex = j;
            }
            int temp = a[i];
            a[i] = a[minIdex];
            a[minIdex] = temp;
        }
    }

    @Test
    public void test() {
        int[] a=new int[]{34,6,2,5,7,4,1};
        doSort(a);
        for (int i : a) {
            System.out.print(i + " ");
        }
    }
}
