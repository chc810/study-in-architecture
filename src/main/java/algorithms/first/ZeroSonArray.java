package algorithms.first;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <dl>
 * <dt>ZeroSonArray</dt>
 * <dd>Description:零子数组</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/4/21</dd>
 * </dl>
 *
 * @author cuihc
 */
public class ZeroSonArray {

    @Test
    public void main() {
        String s = "-5,3,2,7,-6,10";
        List list = new ArrayList<Integer>();
        for (String str : s.split(",")) {
            list.add(Integer.parseInt(str));
        }
        quickSort(list,0,list.size() -1);
        System.out.println(list);
    }


    public void one(List<Integer> intput) {
        //1.求所有前n个数的和
        List<SumObject> sums = new ArrayList<SumObject>();
        for (int i=0;i<intput.size();i++) {
            if (i == 0) {
                sums.add(SumObject.getSumObject(i,intput.get(i)));
            } else {
                sums.add(SumObject.getSumObject(i,sums.get(i -1).sum + intput.get(i)));
            }
        }

        //2.排序


        //3.相邻两个最接近0的和sums数组本身的值取最接近0的，就是所求

    }

    /**
     * 快速排序
     */
    public void quickSort(List< Comparable> list, int left, int right) {
        Comparable key = list.get(left);
        int low = left;
        int high = right;
        while (low < high) {
            while(key.compareTo(list.get(high)) < 0 && low < high) {
                high --;
            }

            while (key.compareTo(list.get(low)) >= 0 && low < high) {
                low ++;
            }
            //交换
            if (low < high) {
                Comparable temp = list.get(low);
                list.set(low, list.get(high));
                list.set(high, temp);
            }
        }
        list.set(left,list.get(low));
        list.set(low,key);

        quickSort(list,left,low);
        quickSort(list,low + 1, right);
    }
}

class SumObject implements  Comparable<SumObject>{
    public int index;
    public int sum;
    public static SumObject getSumObject(int index , int sum) {
        SumObject sumObject = new SumObject();
        sumObject.index = index;
        sumObject.sum = sum;
        return sumObject;
    }

    public int compareTo(SumObject o) {
        return this.sum > o.sum ? 1 : (this.sum < o.sum ? -1 : 0);
    }
}


