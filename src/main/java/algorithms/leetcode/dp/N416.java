package algorithms.leetcode.dp;

/**
 * <dl>
 * <dt>N416</dt>
 * <dd>Description: 416. Partition Equal Subset Sum</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/11/28</dd>
 * </dl>
 *
 * 0-1背包问题
 *
 * @author cuihc
 */
public class N416 {

    class Solution {
        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int i : nums) {
                sum += i;
            }
            if (sum % 2 != 0) {
                return false;
            }
            sum = sum / 2;
            memo = new int[nums.length][sum + 1];
            return solution(nums, 0, sum);
        }

        //记忆化搜索
        private int[][] memo;
        private boolean solution(int[] nums, int index, int sum) {
            if (index <= nums.length && sum == 0) {
                return true;
            }
            if (index == nums.length) {
                return false;
            }
            if (memo[index][sum] == 0) {
                int temp = sum - nums[index];
                if (solution(nums, index + 1, sum) || ((temp >= 0) ? solution(nums, index + 1, temp) : false)) {
                    memo[index][sum] = 1;
                } else {
                    memo[index][sum] = 2;
                }
            }
            return memo[index][sum] == 1 ? true : false;
        }
    }

    class SolutionDp {
        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int i : nums) {
                sum += i;
            }
            if (sum % 2 != 0) {
                return false;
            }
            sum = sum / 2;
            boolean[][] memo = new boolean[nums.length][sum + 1];
            for (int j=0;j<=sum;j++) {
                if (nums[0] == j || j == 0) {
                    memo[0][j] = true;
                    if (j == sum && memo[0][j]) {
                        return true;
                    }
                }
            }

            for (int i = 1;i<nums.length;i++) {
                for (int j=0;j<=sum;j++) {
                    int temp = j - nums[i];
                    memo[i][j] = memo[i-1][j] || (temp >= 0 ? memo[i-1][temp] : false);
                    //下面的逻辑其实可以去掉，加上可以让循环提前结束，只要每一行结尾有true，最后肯定是true
                    if (j == sum && memo[i][j]) {
                        return true;
                    }
                }
            }
            return false;
        }

    }

    public static void main(String[] args) {
        System.out.println(new N416().new Solution().canPartition(new int[]{1, 5, 11, 5}));
        System.out.println(new N416().new SolutionDp().canPartition(new int[]{1, 2, 3, 5}));

    }
}
