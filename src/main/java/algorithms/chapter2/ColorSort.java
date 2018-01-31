package algorithms.chapter2;

import org.junit.Test;

/**
 * <dl>
 * <dt>ColorSort</dt>
 * <dd>Description:荷兰国旗问题： 现有红白蓝三个不同颜色的小球，
 * 乱序排列在一起，请重新排列这些小球，使得红白蓝三色的同颜色的球在一起。
 * 这个问题之所以叫荷兰国旗问题，是因为我们可以将红白蓝三色小球想象成条状物，
 * 有序排列后正好组成荷兰国旗。</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/1/31</dd>
 * </dl>
 *
 * @author cuihc
 */
public class ColorSort {

    @Test
    public void main() {
        int[] a = new int[]{1,0,2,0,1,0,0,1,2};
//        int[] a = new int[]{1,0};
        doSort(a);
        QuickSort.syso(a);
    }

    public void doSort(int[] a) {
        int begin = 0;
        int current = 0;
        int end = a.length - 1;

        while (current <= end) {
            if (a[current] == 1) {
                current++;
            } else if (a[current] == 0) {
                QuickSort.swap(a, current, begin);
                current++;
                begin++;
            } else if (a[current] == 2) {
                QuickSort.swap(a, current, end);
                end--;
            }
        }


    }
}
