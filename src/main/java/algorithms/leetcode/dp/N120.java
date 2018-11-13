package algorithms.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * <dl>
 * <dt>N120</dt>
 * <dd>Description:  120. Triangle</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/11/13</dd>
 * </dl>
 *
 * @author cuihc
 */
public class N120 {

    class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
            int[] list = new int[triangle.size()];
            solution(triangle, triangle.size() - 1, list);
            int ret = list[0];
            for (int i = 1; i< list.length;i++) {
                if (list[i] < ret) {
                    ret = list[i];
                }
            }
            return ret;
        }

        /**
         * 递归思想自顶向下
         * @param triangle
         * @param index
         * @param list
         */
        private void solution(List<List<Integer>> triangle, int index, int[] list) {
            if (index == 0) {
                list[0] = triangle.get(index).get(0);
                return;
            }
            solution(triangle, index - 1, list);
            List<Integer> row = triangle.get(index);
            for (int i=row.size() - 1;i >= 0;i--) {
                if (i == 0) {
                    list[i] = row.get(i) + list[i];
                } else if (i == row.size() - 1) {
                    list[i] = row.get(i) + list[i - 1];
                } else {
                    list[i] = row.get(i) + Math.min(list[i - 1], list[i]);
                }
            }
        }
    }
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);

        List<Integer> list2 = new ArrayList<>();
        list2.add(3); list2.add(4);

        List<Integer> list3 = new ArrayList<>();
        list3.add(6); list3.add(5);list3.add(7);

        List<Integer> list4 = new ArrayList<>();
        list4.add(4); list4.add(1);list4.add(8);list4.add(3);

        List<List<Integer>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);
        System.out.println(new N120().new Solution().minimumTotal(list));
    }
}
