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

        System.out.println(65536 & 65535);




        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

        new Thread( () ->{

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readLock.lock();
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readLock.unlock();
        }).start();

        new Thread( () ->{

            readLock.lock();
            System.out.println("阻塞住了");

            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readLock.unlock();
        }).start();

//        new Thread( () ->{
//
//            writeLock.lock();
//            System.out.println("阻塞住了");
//
//            try {
//                Thread.sleep(100000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            writeLock.unlock();
//        }).start();




    }
}