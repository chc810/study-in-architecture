package algorithms.chapter2;

import org.junit.Test;

/**
 * 堆排序
 */
public class HeapSort {

    @Test
    public void test() {
        int[] a = new int[]{3,6,3,3,3,8,2,5,22,55,3234,33,4,2,3,565,32,21,1,3,4,678899};
        sort(a);
        for (int aa : a) {
            System.out.print(aa + " ");
        }
    }

    public static void sort(int[] a) {
        MaxPQ<Integer> maxPQ = new MaxPQ<>(a.length);
        for (int aa : a) {
            maxPQ.insert(aa);
        }
//        for
    }
}
