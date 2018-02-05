package algorithms.chapter2;

import org.junit.Test;

/**
 * <dl>
 * <dt>E2_4_30</dt>
 * <dd>Description:动态中位数查找</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/2/5</dd>
 * </dl>
 *
 * @author cuihc
 */
public class E2_4_30 {

    @Test
    public void main() {
        MidPQ midPQ = new MidPQ(100);
        midPQ.insert(30);
        System.out.println(midPQ.mid());
        midPQ.insert(100);
        System.out.println(midPQ.mid());
        midPQ.insert(180);
        System.out.println(midPQ.mid());
        midPQ.insert(1);
        System.out.println(midPQ.mid());
        midPQ.insert(181);
        System.out.println(midPQ.mid());
        System.out.println("-------------------------");
        System.out.println(midPQ.delMid());
        System.out.println(midPQ.mid());
        System.out.println(midPQ.delMid());
        System.out.println(midPQ.mid());


    }

}
