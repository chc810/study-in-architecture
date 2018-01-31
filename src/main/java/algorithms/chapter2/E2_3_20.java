package algorithms.chapter2;

import algorithms.common.CommonUtil;
import algorithms.common.Stack;
import org.junit.Test;

/**
 * <dl>
 * <dt>E2_3_20</dt>
 * <dd>Description:非递归的快速排序</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/1/31</dd>
 * </dl>
 *
 * @author cuihc
 */
public class E2_3_20 {

    @Test
    public void main() {
        int[] a = new int[]{3,6,3,3,3,3,56,2,12,65,333,666,1,2,4,666,333,98};
        doSort(a,0,a.length - 1);
        for (int aa : a) {
            System.out.print(aa + " ");
        }
    }

    public void doSort(int[] a, int lo, int hi) {
        if (hi - lo <= 0) {
            return;
        }
        if (hi - lo == 1) {
            if (a[hi] < a[lo]) {
                CommonUtil.swap(a, hi, lo);
            }
            return;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int j = QuickSort.partion(a, lo, hi);
        stack.push(j - 1);
        stack.push(0);
        stack.push(hi);
        stack.push(j+1);
        while (stack.size() > 0) {
            int mLo = stack.pop();
            int mHi = stack.pop();
            if (mHi - mLo <= 0) {
                continue;
            }
            if (mHi - mLo == 1) {
                if (a[mHi] < a[mLo]) {
                    CommonUtil.swap(a, mHi, mLo);
                }
                continue;
            }
            int mj = QuickSort.partion(a, mLo, mHi);
            stack.push(mj - 1);
            stack.push(mLo);
            stack.push(mHi);
            stack.push(mj+1);
        }

    }
}
