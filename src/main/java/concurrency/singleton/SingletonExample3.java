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
 *      线程安全的懒汉模式
 * @author cuihc
 */
public class SingletonExample3 {

    /**
     * 必须要volatile修饰，因为new 一个对象存在指令重排序的可能
     *
     * 1.memory = allocate()   对象分配内存空间
     * 2. ctorInstance()      初始化对象
     * 3. instance = memory    设置instance指向刚分配的内存空间
     *
     *
     * 可能出现重排序
     * 1.memory = allocate()   对象分配内存空间
     * 3. instance = memory    设置instance指向刚分配的内存空间
     * 2. ctorInstance()      初始化对象
     *
     *
     */
    private volatile static SingletonExample3 singletonExample1 = null;

    private SingletonExample3() {}

    public static SingletonExample3 getSingleton() {
        if (singletonExample1 == null) {   //此处如果上面描述的3先发生，此处可能获取一个没有初始化好的对象
            synchronized (SingletonExample3.class) {
                if (singletonExample1 == null) {  //double checke
                    singletonExample1 = new SingletonExample3();
                }
            }
        }
        return singletonExample1;
    }
}
