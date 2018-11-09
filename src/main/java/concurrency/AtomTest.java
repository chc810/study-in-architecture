package concurrency;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <dl>
 * <dt>AtomTest</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/5/27</dd>
 * </dl>
 *
 * @author cuihc
 */
public class AtomTest {

    @Test
    public void test() {
        AtomicInteger atomicInteger = new AtomicInteger(2);
        atomicInteger.addAndGet(3);
    }
}
