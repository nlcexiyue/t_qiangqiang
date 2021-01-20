package com.qiangqiang.threadlocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalTest1 {
    private String content;

    HashMap map = new HashMap();


    ThreadLocal<String> threadLocal = new ThreadLocal<>();



    public String getContent() {
        return threadLocal.get();
    }

    public void setContent(String content) {
        threadLocal.set(content);
    }

    public static void main(String[] args) {

        ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
        threadLocal1.set("这是");
        int i1 = threadLocal1.hashCode();
        System.out.println("1:" +i1);

        ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
        threadLocal2.set("这是");
        int i2 = threadLocal2.hashCode();
        System.out.println("2:" + i2);


        ThreadLocalTest1 test1 = new ThreadLocalTest1();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test1.setContent(Thread.currentThread().getName());

                    System.out.println("__________________");
                    System.out.println(Thread.currentThread().getName() + "---" +test1.getContent());
                }
            }).start();
        }
    }
}
