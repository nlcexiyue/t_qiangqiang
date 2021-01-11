package com.qiangqiang.Proxy;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/10/21
 * \* Time: 17:12
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class SussImpl implements Suss{
    @Override
    public void sayHello(String say) {
        System.out.println("我要说话"+say);
    }
}