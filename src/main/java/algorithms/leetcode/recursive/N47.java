package algorithms.leetcode.recursive;

import java.util.*;

/**
 * <dl>
 * <dt>N47</dt>
 * <dd>Description: 46. Permutaitons2  排列2</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/11/6</dd>
 * </dl>
 *
 * 允许输入可以有重复的
 * @author cuihc
 */
public class N47 {

    class Solution {

        List<List<Integer>> lists = new ArrayList<>();
        private int[] selected;
        public List<List<Integer>> permuteUnique(int[] nums) {
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
            //记录本次循环重复的数
            Set<Integer> set = new HashSet<>();
            for (int i = 0;i<nums.length;i++) {
                if ( selected[i] == 0 && !set.contains(nums[i])) {
                    stack.push(nums[i]);
                    selected[i] = 1;
                    set.add(nums[i]);
                    solution(nums, stack);
                    stack.pop();
                    selected[i] = 0;
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(new N47().new Solution().permuteUnique(new int[]{1,1,2}));
    }
}
