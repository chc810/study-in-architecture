package algorithms.first;

import org.junit.Test;

/**
 * <dl>
 * <dt>Hanoi</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/5/17</dd>
 * </dl>
 *
 * @author cuihc
 */
public class Hanoi {

    @Test
    public void main() {
        hanoi(3,'a','b','c');
    }

    public void hanoi(int n , char a, char b, char c) {
        if (n == 1) {
            move(a,c);
            return;
        }
        hanoi(n-1,a,c,b);
        move(a,c);
        hanoi(n-1,b,a,c);
    }

    private void move(char a, char b) {
        System.out.println(a + "-->" + b);
    }
}
