package proxy;

import javassist.*;

/**
 * <dl>
 * <dt>MyTranslator</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/4/5</dd>
 * </dl>
 *
 * @author cuihc
 */
public class MyTranslator implements Translator {

    public void start(ClassPool classPool) throws NotFoundException, CannotCompileException {
        System.out.println("-----------start---------");
    }

    /**类加载到JVM前进行代码织入*/
    public void onLoad(ClassPool pool, String classname) throws NotFoundException, CannotCompileException {
        System.out.println("-----------onLoad---------" + classname);
        ClassLoader cl = MyTranslator.class.getClassLoader();
        while(cl != null){
            System.out.print(cl.getClass().getName()+"->");
            cl = cl.getParent();
        }
        System.out.println(cl);
        //判断需要织入的类
        if(!"proxy.Buu".equals(classname)) {
            return ;
        }

        //通过获取类文件
        CtClass cc = pool.get(classname);
        //获得指定方法名的方法
        CtMethod m = cc.getDeclaredMethod("doSomeThing");
        //在方法执行前插入代码
        m.insertBefore("{System.out.println(\"记录日志\");}");

    }

    public static void main(String[] args) {
        Buu buu = new Buu();
        buu.doSomeThing();
    }
}
