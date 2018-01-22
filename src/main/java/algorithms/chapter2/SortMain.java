package algorithms.chapter2;

import org.apache.storm.shade.org.apache.commons.lang.time.StopWatch;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;

public class SortMain {
    private int[] readNums() {
        int[] nums = new int[100 * 1000 * 1000 / 4];
        File file = new File("E:\\Temp\\nums");
        DataInputStream dataInputStream = null;
        try {
            dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
            for (int i = 0;i<nums.length;i++) {
                nums[i] = dataInputStream.readInt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nums;
    }

    @Test
    public void test() {
        int[] a = readNums();
        int[] mergeNums = Arrays.copyOf(a,a.length);
        int[] merge1xNums = Arrays.copyOf(a,a.length);
        int[] merge2xNums = Arrays.copyOf(a,a.length);
        System.out.println("read finish...");
        int[] aux = new int[mergeNums.length];
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        MergeSort.merge(mergeNums, aux, 0, (mergeNums.length - 1) / 2, mergeNums.length - 1);
        System.out.println("mergeSort finish。。" + stopWatch.getTime());
        for (int i = 100;i<200;i++) {
            System.out.print(mergeNums[i] + " ");
        }
        System.out.println();
        stopWatch.reset();
        stopWatch.start();
        MergexSort.merge1(merge1xNums, aux, 0, (mergeNums.length - 1) / 2, mergeNums.length - 1);
        System.out.println("MergexSort1 finish。。" + stopWatch.getTime());
        for (int i = 100;i<200;i++) {
            System.out.print(merge1xNums[i] + " ");
        }


        System.out.println();
        stopWatch.reset();
        stopWatch.start();
        MergexSort.merge2(merge2xNums, aux, 0, (mergeNums.length - 1) / 2, mergeNums.length - 1);
        System.out.println("MergexSort2 finish。。" + stopWatch.getTime());
        for (int i = 100;i<200;i++) {
            System.out.print(merge2xNums[i] + " ");
        }
    }
}
