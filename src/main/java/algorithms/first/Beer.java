package algorithms.first;

import org.junit.Test;

/**
 * <dl>
 * <dt>Beer</dt>
 * <dd>Description:啤酒问题</dd>
 * 5个空瓶可以换一瓶啤酒，问：如果喝了N瓶啤酒，需要买多少瓶？
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/5/17</dd>
 * </dl>
 *
 * @author cuihc
 */
public class Beer {

    private static int b = 0;

    @Test
    public void main() {
        System.out.println(drink(26));
        int i =1;
        while (drink(i) < 32) {
            i++;
        }
        System.out.println(i);
    }

    @Test
    public void main1() {
        int n = 501;
        System.out.println(beer(n));
        beer1(n);
        System.out.println(b);
    }

    public int beer(int n) {
        if (n<5) {
            return 0;
        }
        int x = n / 5;
        int y = n % 5;
        return x + beer(x+y);
    }

    public void beer1(int n) {
        if (n<5) {
            return ;
        }
        int x = n / 5;
        int y = n % 5;
        b+=x;
        beer1(x+y);
    }

    public int drink(int n) {
        return n + beer(n);
      /*  beer1(n);
       return n + b;*/
    }
}
