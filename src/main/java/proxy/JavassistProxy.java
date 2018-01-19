package proxy;

import javassist.*;
import org.junit.Test;

/**
 * <dl>
 * <dt>JavassistProxy</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/4/5</dd>
 * </dl>
 *
 * @author cuihc
 */
public class JavassistProxy {

    @Test
    public void test() throws Throwable {
        //启动自定义的类加载器
        //获取存放CtClass的容器ClassPool
        ClassPool classPool = ClassPool.getDefault();
        //创建一个类加载器
        Loader loader = new Loader();
        //增加一个转换器
        loader.addTranslator(classPool, new MyTranslator());
        loader.run("proxy.MyTranslator",null);
    }
}


class Buu {
    static {
        System.out.println("-----------Buu static---------------");

    }

    public void doSomeThing() {
        System.out.println("-----------doSomeThing0000000");
        ClassLoader cl = Buu.class.getClassLoader();
        while(cl != null){
            System.out.print(cl.getClass().getName()+"->");
            cl = cl.getParent();
        }
        System.out.println(cl);
    }
}


