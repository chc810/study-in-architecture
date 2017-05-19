package algorithms.first;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <dl>
 * <dt>MaxSubArray</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/4/12</dd>
 * </dl>
 *
 *
 * 求最大连续子数组
  给定一个数组A[0,…,n-1]，求A的连续子数
 组，使得该子数组的和最大。
  例如
  数组： 1, -2, 3, 10, -4, 7, 2, -5，
  最大子数组：3, 10, -4, 7, 2
 *
 *
 * @author cuihc
 */
public class MaxSubArray {


    @Test
    public void main() {
        String s = "1,-2,3,10,-4,7,2,-5";
        List<Integer> list = new  ArrayList<Integer>();
        for (String str : s.split(",")) {
            list.add(Integer.parseInt(str));
        }
        violence(list);
        System.out.println("");
        dp(list);
        System.out.println("");
        int ret = dc(list,0,list.size()-1);
        System.out.println(ret);
    }

    /**
     * 暴力求解法
     */
    public void violence(List<Integer> input) {
        int max = 0;
        int inputSize = input.size();
        int start = 0;
        int end = 0;
        for (int i=0;i<inputSize; i++) {
            int tempSum = 0;
            for (int j = i; j < inputSize; j++) {
                tempSum += input.get(j);
                if (tempSum > max) {
                    max = tempSum;
                    start = i;
                    end = j;
                }
            }
        }
        for (int k = start; k<= end; k++) {
            System.out.print(input.get(k) + " ");
        }
    }

    /**
     * 动态规划求解
     *
     *  记S[i]为以A[i]结尾的数组中和最大的子数组
      则：S[i+1] = max(S[i]+A[i+1], A[i+1])
      S[0]=A[0]
      遍历i： 0≤i ≤n-1
      动态规划：最优子问题
      时间复杂度：O(n)
     * @param input
     */
    public void dp(List<Integer> input) {
        int inputSize = input.size();
        int[][] s = new int[inputSize][3];   //记录动态规划中每个状态的和
        s[0][0] = input.get(0);  //到A[i] 最大和
        s[0][1] = 0;  //到A[i]为止，从哪个索引开始是最大的
        s[0][2] = 0;  //A[i]在数组中的索引，位置
        int max =  s[0][0];
        int maxIdex = 0;
        for (int i=1;i<inputSize;i++) {
           int tmp = input.get(i);
            if (s[i-1][0] +  tmp > tmp) {
                s[i][0] = s[i-1][0] +  tmp;
                s[i][1] = s[i-1][1];
                s[i][2] = i;
            } else {
                s[i][0] = tmp;
                s[i][1] = s[i][2] = i;
            }
            if (s[i][0] > max) {
                max = s[i][0];
                maxIdex = i;
            }
        }
        for (int j = s[maxIdex][1]; j<=s[maxIdex][2];j++) {
            System.out.print(input.get(j) + " ");
        }
    }

    /**'
     * 分治法
     * @param input
     * @param start
     * @param end
     * @return
     */
    public int dc(List<Integer> input, int start, int end) {
        if (end == start) {
            return input.get(start);
        }
        int mid = (end + start) / 2;
        int fisrtSum = dc(input, start, mid);
        int endSum = dc(input, mid + 1, end);
        int maxTail = input.get(mid);
        int tmpTail = maxTail;
        int maxPre = input.get(mid + 1);
        int tmpPre = maxPre;
        for (int i = mid - 1; i>=start;i--) {
            tmpTail += input.get(i);
            if (tmpTail > maxTail) {
                maxTail = tmpTail;
            }
        }
        for (int i=mid+2;i <= end; i++)  {
            tmpPre += input.get(i);
            if (tmpPre > maxPre) {
                maxPre = tmpPre;
            }
        }
        int ret = fisrtSum;
        if (endSum > ret) {
            ret = endSum;
        }
        if (maxTail + maxPre > ret) {
            ret = maxTail + maxPre;
        }
        return ret;
    }


}
