package algorithms.chapter2;

import org.apache.storm.shade.org.apache.commons.lang.time.StopWatch;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

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

    /**
     * 生成随机数据用于排序
     */
    @Test
    public void genaralNum() {
//        int num = 100 * 1000 * 1000 / 4;
        int num = 100 * 1000*10 /4;
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

        File file = new File("E:\\Temp\\numsmid");
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

   private int[] readNums() {
       int[] nums = new int[100 * 1000*10 /4];
       File file = new File("E:\\Temp\\numsmid");
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
   public void testRandom() {
        for (int i=0;i<100;i++) {
            System.out.println(getRandom(10));
        }
   }

   @Test
   public void testFile() throws IOException {
       File file = new File("E:\\Temp\\nums");
       if (!file.exists()) {
           file.createNewFile();
       }
//       FileOutputStream fileOutputStream = new FileOutputStream(file);
   }

   private int getRandom(int num) {
       return (int)(Math.random() * num) + 1;
   }


    @Test
    public void doSort() {
        int[] a = new int[]{7,2};
        int[] aux = new int[a.length];
        merge(a, aux, 0, (a.length - 1) / 2, a.length - 1);
        for (int aa : a) {
            System.out.print(aa + " ");
        }
    }

    @Test
    public void doMergeSort() {
        int[] a = readNums();
        int[] mergeNums = Arrays.copyOf(a,a.length);
        int[] selectNums = Arrays.copyOf(a,a.length);
        int[] insertNums = Arrays.copyOf(a,a.length);
        System.out.println("read finish...");
        int[] aux = new int[mergeNums.length];
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        merge(mergeNums, aux, 0, (mergeNums.length - 1) / 2, mergeNums.length - 1);
        System.out.println("mergeSort finish。。" + stopWatch.getTime());
        for (int i = 100;i<200;i++) {
            System.out.print(mergeNums[i] + " ");
        }
        System.out.println();
        stopWatch.reset();
        stopWatch.start();
        SelectSort.doSort(selectNums);
        System.out.println("selectSort finish。。" + stopWatch.getTime());
        for (int i = 100;i<200;i++) {
            System.out.print(selectNums[i] + " ");
        }
        System.out.println();
        stopWatch.reset();
        stopWatch.start();
        InsertSort.doSort(insertNums);
        System.out.println("InsertSort finish。。" + stopWatch.getTime());
        for (int i = 100;i<200;i++) {
            System.out.print(insertNums[i] + " ");
        }
    }

    public static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
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
