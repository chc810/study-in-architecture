package algorithms.first;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * <dl>
 * <dt>SortAlgorithms</dt>
 * <dd>Description:排序算法总结</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/5/22</dd>
 * </dl>
 *
 * @author cuihc
 */
public class SortAlgorithms {


    @Test
    public void main() {
        List<Integer> list = Arrays.asList(new Integer[]{3,5,1,4,9,8,1});
//        insertSort(list);
//        insertSortR(list,0);
        bubleSort(list);
        System.out.println(list);
    }

    @Test
    public void main1() {
        List<Integer> list = Arrays.asList(new Integer[]{3,5,1,4,9,8,1});
//        insertSortR2(list,list.size() - 1);
        bubleSortR(list,list.size()-1);
        System.out.println(list);
    }

    @Test
    public void main2() {
        List<Integer> list = Arrays.asList(new Integer[]{3,5,1,4,9,8,1});
//        initHeap(list);
        list.remove(list.size() - 1);
        System.out.println(list);
//        System.out.println(heapSort(list));
    }

    /**
     * 非递归插入排序
     * @param list
     */
    public void insertSort(List<Integer> list) {
        for (int i=0;i<list.size();i++) {
            int temp = list.get(i);
            int j = i -1;
            for (;j>=0;j--) {
                if (temp < list.get(j)) {
                    //后移
                    list.set(j+1, list.get(j));
                } else {
                    break;
                }
            }
            list.set(j + 1, temp);
        }
    }

    /**
     * 非递归冒泡排序
     * @param list
     */
    public void bubleSort(List<Integer> list) {
        if (list.size() <= 1) {
            return;
        }
        for (int i = 1;i<list.size();i++) {
            for (int j = i;j>=1;j--) {
                if (list.get(j) < list.get(j - 1)) {
                    swap(list,j,j-1);
                }
            }
        }
    }

    /**
     * 初始化堆
     * @param list
     */
    public List<Integer> heapSort(List<Integer> list) {
        //初始化堆
        int lastParent = list.size() / 2 - 1;
      /*  for (int i = lastParent; i >=0;i-- ) {
            adjustHeap(list, i);
        }
        //
        List<Integer> ret = new ArrayList<Integer>();
        int i = 0;
        while (i<=list.size() - 1) {
            ret.add(list.get(i));
            swap(list,i,list.size() - 1);
            list.remove(list.size() - 1);
            adjustHeap(list,0);
        }
        return ret;*/
      return null;
    }

    /**
     * 调整为小顶堆
     * @param list
     * @param pos
     */
    /*private void adjustHeap(List<Integer> list, int pos) {
        if (pos * 2 + 1 >= list.size()) {
            return;
        }
        int parent = list.get(pos);
        int leftChild = list.get(pos * 2 + 1);  //左孩子值
        if (pos * 2 + 2 < list.size()) {
            int min = pos;
            if (leftChild < list.get(min)) {
                min = pos * 2 + 1;
            }
            int rightChild = list.get(pos * 2 + 2);
            if (rightChild < list.get(min)) {
                min = pos * 2 + 2;
            }
            if (pos != min) {
                swap(list, pos, min);
                adjustHeap(list, min);   //递归
            }
        } else {
            if (parent > leftChild) {
                adjustHeap(list, pos * 2 + 1);
            }
        }
    }*/

    private void adjustHeap(List<Integer> list, int pos, int last) {
        if (pos * 2 + 1 >= last) {
            return;
        }
        int parent = list.get(pos);
        int leftChild = list.get(pos * 2 + 1);  //左孩子值
        if (pos * 2 + 2 < list.size()) {
            int min = pos;
            if (leftChild < list.get(min)) {
                min = pos * 2 + 1;
            }
            int rightChild = list.get(pos * 2 + 2);
            if (rightChild < list.get(min)) {
                min = pos * 2 + 2;
            }
            if (pos != min) {
                swap(list, pos, min);
                adjustHeap(list, min, last);   //递归
            }
        } else {
            if (parent > leftChild) {
                adjustHeap(list, pos * 2 + 1, last);
            }
        }
    }

    /**
     * 递归冒泡排序
     * @param list
     * @param pos
     */
    public void bubleSortR(List<Integer> list, int pos) {
        if (pos <= 0) {
            return;
        }
        bubleSortR(list, pos - 1);
        int t = pos;
        while (t - 1 >= 0 && list.get(t) < list.get(t - 1)) {
            swap(list, t, t -1);
            t--;
        }
    }

    /**
     * 递归插入排序
     * @param list
     * @param pos
     */
    public void insertSortR(List<Integer> list, int pos) {
        if (pos >= list.size()) {
            return;
        }
        if (pos > 0) {
            int i = pos - 1;
            int temp = list.get(pos);
            while (i >= 0 && list.get(i) > temp) {
                list.set(i+1, list.get(i));
                i--;
            }
            list.set(i + 1, temp);
        }
        insertSortR(list,pos + 1);
    }

    /**
     * 递归插入排序
     * @param list
     * @param pos
     */
    public void insertSortR2(List<Integer> list, int pos) {
        if (pos < 0) {
            return;
        }
        insertSortR2(list,pos - 1);
        if (pos > 0) {
            int i = pos - 1;
            int temp = list.get(pos);
            while (i >= 0 && list.get(i) > temp) {
                list.set(i+1, list.get(i));
                i--;
            }
            list.set(i + 1, temp);
        }

    }

    /**
     * 数组中两个元素交换
     * @param list
     * @param i
     * @param j
     */
    private void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i,list.get(j));
        list.set(j, temp);
    }
}
