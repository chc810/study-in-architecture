package algorithms.leetcode.dp;

/**
 * 322. Coin Change
 */
public class N322 {

    class Solution {
        public int coinChange(int[] coins, int amount) {
            //dp解法
            int[] memo = new int[amount + 1];
            /*for (int i=0; i< memo.length;i++) {
                memo[i] = -1;
            }*/
            for (int i = 1;i<=amount;i++) {
                int num = Integer.MAX_VALUE;
                for (int j =0;j<coins.length;j++) {
                    if (i >= coins[j]) {
                        int temp = i - coins[j];
                        num = (num > memo[temp] + 1 ? memo[temp] + 1 : num);
                    }
                }
                memo[i] = num;
            }
            if (memo[amount] != 0) {
                return memo[amount];
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        int i = new N322().new Solution().coinChange(new int[]{2}, 3);
        System.out.println(i);
    }
}
