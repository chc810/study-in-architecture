package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * <dl>
 * <dt>CglibProxy</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/4/5</dd>
 * </dl>
 *
 * @author cuihc
 */
public class CglibProxy {

    @Test
    public void test() {
        //创建一个织入器
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(Business.class);
        //设置需要织入的逻辑
        enhancer.setCallback(new LogIntercept());
        //使用织入器创建子类
        Business business = (Business)enhancer.create();
        business.doSomeThing();

    }
}

class Business {
    static {
        System.out.println("---------static-----");
    }
    public void doSomeThing() {
        System.out.println("doSomeThing---------------" + this.getClass().getName());
    }
}


class LogIntercept implements MethodInterceptor {

    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //执行原有逻辑
        Object ret = methodProxy.invokeSuper(target,args);
        //执行织入逻辑
        if (method.getName().equals("doSomeThing")) {
            System.out.println("记录日志");
        }
        return ret;
    }
}
