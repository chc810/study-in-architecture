package algorithms.leetcode.dp;

/**
 * <dl>
 * <dt>N70</dt>
 * <dd>Description: 70. Climbing Stairs</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/11/10</dd>
 * </dl>
 *
 *
 * @author cuihc
 */
public class N70 {

    class Solution {
        int[] memo;

        public int climbStairs(int n) {
            memo = new int[n + 1];
            for (int i = 0;i<memo.length;i++) {
                memo[i] = -1;
            }
//            return solution(0, n);
//            return solution1(n);
            return dpSolution(n);
        }

        /**
         * 思考的过程是从一步两步开始试
         * @param index
         * @param n
         * @return
         */
        private int solution(int index, int n) {
            if (index == n) {
                return 1;
            }
            if (index > n) {
                return 0;
            }
            if (memo[index] == -1) {
                memo[index] = solution(index + 1, n) + solution(index + 2, n);
            }
            return memo[index];
        }

        /**
         * 思考过程是从最后考虑，上到楼顶，最后一步要么走一步要么走两步
         * @param index
         * @return
         */
        private int solution1(int index) {
            if (index <= 1) {
                return 1;
            }
            if (memo[index] == -1) {
                memo[index] = solution1(index - 1) + solution1(index - 2);
            }
            return memo[index];
        }

        /**
         * 动态规划的解法
         * @param n
         * @return
         */
        private int dpSolution(int n) {
            if (n <= 1) {
                return 1;
            }
            int[] mem = new int[n + 1];
            mem[1] = 1;
            mem[2] = 2;
            for (int i = 3; i<= n;i++) {
                mem[i] = mem[i-1] + mem[i-2];
            }
            return mem[n];
        }
    }

    public static void main(String[] args) {
        System.out.println(new N70().new Solution().climbStairs(40));
    }
}
