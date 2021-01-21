package com.qiangqiang.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/21
 * \* Time: 17:51
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ReadLockTest {

    static  int a = 1;
    public static void main(String[] args) throws InterruptedException {




        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();



        for (int i = 0; i < 100; i++) {

            new Thread( () ->{

                readLock.lock();

                a++;

                System.out.println(a);

//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                readLock.unlock();

            }).start();
        }

        for (int i = 0; i < 10000; i++) {
            a++;

        }

        Thread.sleep(1000);
        System.out.println("最后的a :" + a);



//        Object o = new Object();
//        for (int i = 0; i < 10; i++) {
//
//            new Thread( () ->{
//
//                synchronized (o){
//                    System.out.println("111");
//
//                    try {
//                        Thread.sleep(10000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//
//            }).start();
//        }



    }
}