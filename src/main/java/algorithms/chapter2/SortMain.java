package algorithms.chapter2;

import org.apache.storm.shade.org.apache.commons.lang.time.StopWatch;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;

public class SortMain {

    /**
     * 生成随机数据用于排序
     */
    @Test
    public void genaralNum() {
        int num = 100 * 1000 * 1000 / 4;
//        int num = 100 * 1000*10 /4;
        int[] nums = new int[num + 1];
        int[] aux = new int[num + 1];
        for (int i=1;i<=num;i++) {
            nums[i] = i;
            aux[i] = i;
        }
        for (int i = aux.length - 1; i>0;i--) {
            int randomIndex = getRandom(i);
            int temp = aux[randomIndex];
            aux[randomIndex] = aux[i];
            aux[i] = temp;
        }

        File file = new File("E:\\Temp\\nums");
        DataOutputStream dataOutputStream = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                dataOutputStream = new DataOutputStream(bufferedOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            for (int i = 1; i<= nums.length -1;i++) {
                dataOutputStream.writeInt(nums[aux[i]]);
            }
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private int getRandom(int num) {
        return (int)(Math.random() * num) + 1;
    }

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
        int[] quickNums = Arrays.copyOf(a,a.length);
        int[] quick1Nums = Arrays.copyOf(a,a.length);
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

        System.out.println();
        stopWatch.reset();
        stopWatch.start();
        QuickSort.sort(quickNums,0, quickNums.length - 1);
        System.out.println("QuickSort finish。。" + stopWatch.getTime());
        for (int i = 100;i<200;i++) {
            System.out.print(quickNums[i] + " ");
        }

        System.out.println();
        stopWatch.reset();
        stopWatch.start();
        //选出最大的放在最后当哨兵
        int maxIndex = 0;
        for (int i=1;i<quick1Nums.length;i++) {
            if (quick1Nums[i] > quick1Nums[maxIndex]) {
                maxIndex = i;
            }
        }
        QuickSort.swap(quick1Nums, maxIndex, quick1Nums.length - 1);
        QuickSort.sort(quick1Nums,0, quick1Nums.length - 1);
        System.out.println("Quick1Sort finish。。" + stopWatch.getTime());
        for (int i = 100;i<200;i++) {
            System.out.print(quick1Nums[i] + " ");
        }
    }
}
