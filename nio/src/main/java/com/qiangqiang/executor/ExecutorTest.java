package com.qiangqiang.executor;

import java.util.concurrent.*;

public class ExecutorTest {
    ExecutorService executorService =
            new ThreadPoolExecutor(10,10,10,
                    TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3));

    public static void main(String[] args) {

        //一池5个处理线程
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //一池一线程
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //一池N个线程
//        ExecutorService executorService = Executors.newCachedThreadPool();


        ExecutorService executorService = new ThreadPoolExecutor(2, 5,
                10000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(3));

        //模式10个用户办理
        try {
            for (int i = 1; i < 10; i++) {
                int finalI = i;
                executorService.execute(() ->{
                    System.out.println(Thread.currentThread().getName() + "办理业务" + finalI);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
//                Thread.sleep(200);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }
}
