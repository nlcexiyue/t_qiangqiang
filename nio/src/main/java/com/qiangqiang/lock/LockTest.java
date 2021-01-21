package com.qiangqiang.lock;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/18
 * \* Time: 9:24
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {

        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        readLock.lock();

        System.out.println("111");

        readLock.unlock();


        Thread.yield();







        LongAdder longAdder = new LongAdder();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();



        Object o1 =new Object();
        System.out.println("无状态" + ClassLayout.parseInstance(o1).toPrintable());

        Thread.sleep(5000);


        Object o =new Object();
        synchronized (o){
            System.out.println("偏向锁" + ClassLayout.parseInstance(o).toPrintable());
        }
        System.out.println("偏向锁" + ClassLayout.parseInstance(o).toPrintable());

        new Thread( () -> {
            synchronized (o){
                System.out.println("轻量级" + ClassLayout.parseInstance(o).toPrintable());
            }

        }).start();

        Thread.sleep(1000);


        for (int i = 0; i < 5; i++) {
            new Thread( () -> {
                synchronized (o){
                    System.out.println("重量级" + ClassLayout.parseInstance(o).toPrintable());
                }

            }).start();
        }







    }

    @Test
    public void TestspringVersionAndspringBootVersion (){
        String springVersion = SpringVersion.getVersion();
        System.out.println(springVersion);
        String springBootVersion = SpringBootVersion.getVersion();
        System.out.println(springBootVersion);
    }
}