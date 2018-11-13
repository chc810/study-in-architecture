package concurrency.singleton;

/**
 * <dl>
 * <dt>SingletonExample1</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/11/13</dd>
 * </dl>
 *
 *      非线程安全的懒汉模式
 * @author cuihc
 */
public class SingletonExample1 {

    private static SingletonExample1 singletonExample1 = null;

    private SingletonExample1() {}

    public static SingletonExample1 getSingleton() {
        if (singletonExample1 == null) {
            singletonExample1 = new SingletonExample1();
        }
        return singletonExample1;
    }
}
