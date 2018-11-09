package concurrency.atomic;

import java.util.concurrent.atomic.AtomicReference;

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
public class CountExample4 {

    public static void main(String[] args) throws InterruptedException {
        AtomicReference<Integer> integerAtomicReference = new AtomicReference<>(0);
        integerAtomicReference.compareAndSet(0, 1);
        System.out.println(integerAtomicReference.get());
    }
}
