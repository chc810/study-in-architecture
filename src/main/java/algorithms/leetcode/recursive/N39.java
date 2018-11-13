package algorithms.leetcode.recursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <dl>
 * <dt>N39</dt>
 * <dd>Description: 39. Combination Sum</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/11/9</dd>
 * </dl>
 *
 *  Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

 The same repeated number may be chosen from candidates unlimited number of times.

 Note:

 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 Example 1:

 Input: candidates = [2,3,6,7], target = 7,
 A solution set is:
 [
 [7],
 [2,2,3]
 ]
 Example 2:

 Input: candidates = [2,3,5], target = 8,
 A solution set is:
 [
 [2,2,2,2],
 [2,3,3],
 [3,5]
 ]
 *
 * @author cuihc
 */
public class N39 {



    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            solution(candidates,0,target, new Stack<>());
            return lists;
        }

        List<List<Integer>> lists = new ArrayList<>();

        private  void solution(int[] candidates, int index, int target, Stack<Integer> stack) {
            if (target == 0) {
                //递归到底，获取一个解
                List<Integer> list = new ArrayList<>();
                for (Integer integer : stack) {
                    list.add(integer);
                }
                lists.add(list);
                return;
            }

            for (int i = index;i<candidates.length;i++) {
                if (target - candidates[i] < 0) {
                    continue;
                }
                stack.push(candidates[i]);
                solution(candidates, i, target - candidates[i], stack);
                stack.pop();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new N39().new Solution().combinationSum(new int[]{2,3,5}, 8) );
    }
}
