package com.qiangqiang.Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("方法被调用了");
                Thread.sleep(2000);
                return 1024;
            }
        });
        Thread t1 = new Thread(integerFutureTask);
        t1.start();
        Thread t2 = new Thread(integerFutureTask);
        t2.start();


        System.out.println("_________________");
        int a = 100;

        Integer integer = integerFutureTask.get();
        System.out.println("最终结果"+(a+integer));

    }
}
