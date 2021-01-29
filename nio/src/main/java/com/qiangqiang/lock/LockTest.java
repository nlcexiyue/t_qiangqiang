package com.qiangqiang.lock;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock reentrantLock = new ReentrantLock(true);

        Thread thread = new Thread(() -> {
            String name = Thread.currentThread().getName();

            reentrantLock.lock();

            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(name);

            reentrantLock.unlock();
        });

        thread.setName("t0");
        thread.start();

        Thread thread1 = new Thread(() -> {
            String name = Thread.currentThread().getName();

            reentrantLock.lock();



            System.out.println(name);

            reentrantLock.unlock();
        });

        thread1.setName("t1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            String name = Thread.currentThread().getName();

            reentrantLock.lock();



            System.out.println(name);

            reentrantLock.unlock();
        });

        thread2.setName("t2");
        thread2.start();






    }
}
