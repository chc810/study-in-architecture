package concurrency.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * <dl>
 * <dt>CountExample1</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/11/7</dd>
 * </dl>
 *
 *
 *
 * @author cuihc
 */
public class CountExample5 {

    private static  AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(CountExample5.class,
            "count");

    /**
     * 必须使用volatile修饰，并且不能使用static
     */
    private volatile int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static void main(String[] args) throws InterruptedException {
        CountExample5 countExample5 = new CountExample5();
       updater.compareAndSet(countExample5, 0, 1);
       System.out.println(countExample5.getCount());
    }
}
