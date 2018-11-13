package concurrency.publish;

/**
 * <dl>
 * <dt>UnsafePublish</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/11/12</dd>
 * </dl>
 *
 * 不安全发布，内部类使用了外部类的this对象
 * @author cuihc
 */
public class UnsafePublish {

    private int i = 10;

    public UnsafePublish() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            //只有内部类可以有这种写法
            System.out.println(UnsafePublish.this.i);
        }
    }
    public static void main(String[] args) {
        new UnsafePublish();
    }
}
