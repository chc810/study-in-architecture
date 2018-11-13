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
 *      线程安全的单例模式，使用枚举
 *      比较推荐的写法，在实际使用时才构造
 *
 *
 * @author cuihc
 */
public class SingletonExample5 {


    private SingletonExample5() {}

    public static SingletonExample5 getSingleton() {
        return Singleton.INSTANCE.getInstance();
    }

    public static SingletonExample5 getSingleton1() {
        return Singleton.OTHER.getInstance();
    }

    /**
     * 只定义一个INSTANCE是正确的写法
     * 定义两个是为了测试，这种情况下构造方法执行两遍，获取的对象是两个
     */
    private enum Singleton {
        INSTANCE, OTHER;

        private SingletonExample5 instance = null;

        //JVM保证此方法绝对只执行一次
        Singleton() {
            System.out.println("singleton");
            instance = new SingletonExample5();
        }

        public SingletonExample5 getInstance() {
            return instance;
        }
    }

    public static void main(String[] args) {
                System.out.println(SingletonExample5.getSingleton());
        System.out.println(SingletonExample5.getSingleton1());
    }
}
