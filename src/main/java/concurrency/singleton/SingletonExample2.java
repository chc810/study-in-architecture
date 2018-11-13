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
 *      线程安全的饿汉模式
 *
 *      如果实际不使用此对象，会造成资源浪费
 * @author cuihc
 */
public class SingletonExample2 {

    private static SingletonExample2 singletonExample1 = new SingletonExample2();

    private SingletonExample2() {}

    public static SingletonExample2 getSingleton() {
        return singletonExample1;
    }
}
