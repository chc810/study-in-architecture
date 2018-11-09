package algorithms.leetcode.recursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <dl>
 * <dt>N77</dt>
 * <dd>Description: 77. combinations 组合问题</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/11/8</dd>
 * </dl>
 *
 *
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

 Example:

 Input: n = 4, k = 2
 Output:
 [
 [2,4],
 [3,4],
 [2,3],
 [1,2],
 [1,3],
 [1,4],
 ]
 * @author cuihc
 */
public class N77 {

    class Solution {

        List<List<Integer>> lists = new ArrayList<>();
        public List<List<Integer>> combine(int n, int k) {
            if (k > n) {
                return null;
            }
            solution(n,k,1,new Stack<>());
            return lists;
        }

        private void solution(int n, int k, int index, Stack<Integer> stack) {

            if (k == 0) {
                //递归到底，获取一个解
                List<Integer> list = new ArrayList<>();
                for (Integer integer : stack) {
                    list.add(integer);
                }
                lists.add(list);
                return;
            }

            for (int i = index; i<= n; i++) {
                //剪枝处理
                if (n - i < k - 1) {
                    return;
                }
                stack.push(i);
                solution(n, k - 1, i + 1, stack);
                stack.pop();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new N77().new Solution().combine(4,1));
    }
}
