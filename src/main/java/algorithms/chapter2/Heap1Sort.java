package algorithms.chapter2;

import algorithms.common.CommonUtil;
import org.junit.Test;

/**
 * 堆排序，改进，无需交换的堆2.4.26
 */
public class Heap1Sort {

    @Test
    public void test() {
        int[] a = new int[]{-1,3,6,1,2,45345,3,5,2,5,777,2,54,89,22};
//        int[] a = new int[]{-1,3,6,1,2,45345};
        sort(a);
        CommonUtil.syso(a);
    }

    public static void sort(int[] a) {
        for (int i = 2; i < a.length; i++) {
            swim(a, i);
        }
        for (int i = a.length - 1; i>=2;i--) {
            CommonUtil.swap(a, i, 1);
            sink(1,a[1], a, i - 1);
        }
    }

    private static void swim(int[] a, int i) {
        int temp = i;
        int tempValue = a[temp];
        while(temp > 1) {
            int parent = parent(temp);
            if (a[parent]< tempValue) {
                a[temp] = a[parent];
                temp = parent;
            } else {
                break;
            }
        }
        a[temp] = tempValue;
    }

    private static void sink(int index,int value, int[] a, int N) {
        if (index >= N) {
            return;
        }
        int max = value;
        int maxIndex = index;
        if (left(index) <= N && a[left(index)] > max) {
            maxIndex = left(index);
            max = a[maxIndex];
        }
        if (right(index) <= N && a[right(index)] > max) {
            maxIndex = right(index);
            max = a[maxIndex];
        }
        if (maxIndex != index) {
            a[index] = max;
            sink(maxIndex,value, a, N);
        } else {
            a[index] = value;
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
