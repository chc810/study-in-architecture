package algorithms.leetcode.recursive;

import java.util.*;

/**
 * <dl>
 * <dt>N40</dt>
 * <dd>Description:  40. Combination Sum II</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/11/10</dd>
 * </dl>
 *
 * @author cuihc
 */
public class N40 {

    class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            //注意此处排序的妙用
            Arrays.sort(candidates);
            solution(candidates,0, target, new Stack<>());
            return lists;
        }

        List<List<Integer>> lists = new ArrayList<>();

       /* private  void solution(int[] candidates, int index,int target, Stack<Integer> stack) {
            if (target == 0) {
                //递归到底，获取一个解
                List<Integer> list = new ArrayList<>();
                for (Integer integer : stack) {
                    list.add(integer);
                }
                lists.add(list);
                return;
            }

            Set<Integer> set = new HashSet<>();
            for (int i = index;i<candidates.length;i++) {
                if (target - candidates[i] < 0 ) {
                    return;
                }
                if (set.contains(candidates[i])) {
                    continue;
                }
                stack.push(candidates[i]);
                set.add(candidates[i]);
                solution(candidates,i + 1,target - candidates[i], stack);
                stack.pop();
            }
        }*/

       //不用set保存做过的方法
        private  void solution(int[] candidates, int index,int target, Stack<Integer> stack) {
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
                if (target - candidates[i] < 0 ) {
                    return;
                }
                //注意此处的写法，排序后，保证每次for循环不取相同的数
                if (i > index && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                stack.push(candidates[i]);
                solution(candidates,i + 1,target - candidates[i], stack);
                stack.pop();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new N40().new Solution().combinationSum2(new int[]{2,5,2,1,2}, 5));
    }
}
