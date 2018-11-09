package algorithms.leetcode.recursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <dl>
 * <dt>N46</dt>
 * <dd>Description: 46. Permutaitons  排列问题</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/11/6</dd>
 * </dl>
 *
 * Given a collection of distinct integers, return all possible permutations.

 Example:

 Input: [1,2,3]
 Output:
 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]
 *
 * @author cuihc
 */
public class N46 {

    class Solution {

        private int[] selected;

        List<List<Integer>> lists = new ArrayList<>();
        public List<List<Integer>> permute(int[] nums) {
            if (nums.length == 0) {
                return null;
            }
            selected = new int[nums.length];
            solution(nums, new Stack<Integer>());
            return lists;
        }

        /**
         * 从index开始的数字
         * @param nums
         * @param stack
         */
        private void solution(int[] nums, Stack<Integer> stack) {

            if (stack.size() == nums.length) {
                //递归到底，获取一个解
                List<Integer> list = new ArrayList<>();
                for (Integer integer : stack) {
                    list.add(integer);
                }
                lists.add(list);
                return;
            }

            for (int i = 0;i<nums.length;i++) {
                if ( selected[i] == 0) {
                    stack.push(nums[i]);
                    selected[i] = 1;
                    solution(nums, stack);
                    stack.pop();
                    selected[i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new N46().new Solution().permute(new int[]{1,2,3}));
    }
}
