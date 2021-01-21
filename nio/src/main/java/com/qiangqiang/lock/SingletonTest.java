package com.qiangqiang.lock;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/21
 * \* Time: 14:08
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class SingletonTest {

    public static void main(String[] args) {
        ClassLoader classLoader  = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return super.loadClass(name);
            }
        };



        for (int i = 0; i < 5; i++) {
            new Thread(() -> {


                SingletonEnum instance = SingletonEnum.INSTANCE;
                System.out.println(Thread.currentThread().getName() + " ++ "  + instance.getValue());
                instance.setValue(2);
                System.out.println(Thread.currentThread().getName() + " ++ "  + instance.getValue());


            }).start();
        }






    }
}