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
 *      第二种写法
 * @author cuihc
 */
public class SingletonExample4 {

    //此行必须在下面静态块的上面，因为是顺序执行的，需要特别注意
    private static SingletonExample4 singletonExample1 = null;

    static {
        singletonExample1 = new SingletonExample4();
    }

    private SingletonExample4() {}

    public static SingletonExample4 getSingleton() {
        return singletonExample1;
    }
}
