package algorithms.chapter1;

import org.junit.Test;

/**
 * <dl>
 * <dt>E1_4_34</dt>
 * <dd>Description:hot或cold，假设距离相等为hot</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/1/12</dd>
 * </dl>
 *
 * @author cuihc
 */
public class E1_4_34 {

    private static int num = 0;

    @Test
    public void main() {
     System.out.println(solution(6000450000234L,546487L));
     System.out.println("调用总数=" + num);
     System.out.println(Integer.MAX_VALUE);
    }

    private long solution(long N, long secretKey) {
        //first guess
        long firstMid = N/2;
        if (firstMid == secretKey) {
            return firstMid;
        }
        long firstDis = Math.abs(firstMid - secretKey);
        //second guess，用小的一边猜测
        long secondMid = N/4;
        if (secondMid == secretKey) {
            return secondMid;
        }
        long secondDis = Math.abs(secondMid - secretKey);
        long low = 0;
        long hi = 0;
        if (firstDis < secondDis) {
            //cold
            low = (long)Math.ceil((firstMid + secondMid) / 2.0);
            hi = N;
        } else {
            //hot
            low = 1;
            hi = (long)Math.floor((firstMid + secondMid) / 2.0);
        }
        return find(secretKey, secondMid, low, hi);
    }

    private long find (long secretKey, long lastGuess, long low, long hi) {
        num++;
        if (low > hi) {
            return -1;
        }
        long guess = (low + hi) / 2;
        if  (guess == secretKey) {
            return guess;
        }
        if (guess == lastGuess) {
            guess += 1;
        }
        long firstDis = Math.abs(lastGuess - secretKey);
        long secondDis = Math.abs(guess - secretKey);
        if (firstDis < secondDis) {
            //cold
            if (guess < lastGuess) {
                low = (long)Math.ceil((lastGuess + guess) / 2.0);
            } else {
                hi = (long)Math.ceil((lastGuess + guess) / 2.0);
            }
        } else {
            //hot
            if (guess < lastGuess) {
                hi = (int)Math.floor((lastGuess + guess) / 2.0);
            } else {
                low = (int)Math.floor((lastGuess + guess) / 2.0);
            }
        }
        return find(secretKey,guess,low,hi);

    }
}
