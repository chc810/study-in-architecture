package algorithms.chapter2;

import algorithms.common.CommonUtil;
import org.junit.Test;

/**
 * 堆排序
 */
public class HeapSort {

    @Test
    public void test() {
        int[] a = new int[]{-1,3,6,1,2,45345,3,5,2,5,777,2,54,89,22};
        sort(a);
        CommonUtil.syso(a);
    }

    public static void sort(int[] a) {
        for (int i = 2; i < a.length; i++) {
            swim(a, i);
        }
        for (int i = a.length - 1; i>=2;i--) {
            CommonUtil.swap(a, i, 1);
            sink(1, a, i - 1);
        }
    }

    private static void swim(int[] a, int i) {
        int temp = i;
        while(temp > 1) {
            int parent = parent(temp);
            if (a[parent]< a[temp]) {
                CommonUtil.swap(a, parent, temp);
                temp = parent;
            } else {
                break;
            }
        }
    }

    private static void sink(int index, int[] a, int N) {
        if (index >= N) {
            return;
        }
        int max = index;
        if (left(index) <= N && a[left(index)] > a[max]) {
            max = left(index);
        }
        if (right(index) <= N && a[right(index)] > a[max]) {
            max = right(index);
        }
        if (max != index) {
            CommonUtil.swap(a,index, max);
            sink(max, a, N);
        }
    }

    private static int parent(int index) {
        return index / 2;
    }
    private static int left(int index) {
        return 2 * index;
    }
    private static int right(int index) {
        return left(index) + 1;
    }
}
