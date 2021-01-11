package com.qiangqiang.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/10/21
 * \* Time: 16:58
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MyInvocationHandler implements InvocationHandler {

    private Suss suss;

    public MyInvocationHandler(Suss suss){
        this.suss = suss;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理类开始");
        System.out.println("Proxy---："+proxy.getClass().getName());
        System.out.println("method---:" + method.getName());
        for (Object arg : args) {
            System.out.println("arg" + arg);
        }
        method.invoke(suss,args);
        System.out.println("结束代理");

        return null;
    }


    public static void main(String[] args) {
        Suss suss = new SussImpl();

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?>[] interfaces = suss.getClass().getInterfaces();
        MyInvocationHandler h = new MyInvocationHandler(suss);
        Suss proxy = (Suss) Proxy.newProxyInstance(loader, interfaces, h);
        proxy.sayHello("我说话");

    }
}