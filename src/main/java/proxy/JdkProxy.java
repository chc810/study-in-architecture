package proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <dl>
 * <dt>JdkProxy</dt>
 * <dd>Description:jdk动态代理</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/4/1</dd>
 * </dl>
 *
 * @author cuihc
 */
public class JdkProxy {

    @Test
    public void test() {
        //实例化目标对象
        UseService useService = new UseServiceImpl();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(useService);
        UseService tar = (UseService)myInvocationHandler.getProxy();
        tar.del();

    }

}

//代理处理逻辑，相当于AOP中的Advice
class MyInvocationHandler implements InvocationHandler{

    //需要代理的对象
    Object target;

    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    //执行目标对象的方法
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--------------before---------------------" + this + ",method=" + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("--------------after---------------------");
        return result;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),this);
    }
}

class UseServiceImpl implements UseService{

    public void add() {
        System.out.println("----------------add-------------------" + this);
    }

    public void del() {
        System.out.println("----------------del-------------------" + this.getClass().getName());
    }
}


interface UseService {

    void add();

    void del();
}
