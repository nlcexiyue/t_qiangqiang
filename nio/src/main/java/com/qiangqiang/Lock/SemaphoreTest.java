package com.qiangqiang.Lock;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(4);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {



                    semaphore.acquire();
                    System.out.println("抢到车位");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                    System.out.println("释放车位");
                }
            },String.valueOf(i)).start();

        }


    }
}
