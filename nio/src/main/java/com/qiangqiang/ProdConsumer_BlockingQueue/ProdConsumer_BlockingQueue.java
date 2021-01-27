package com.qiangqiang.ProdConsumer_BlockingQueue;

//这个案例将会把所有的知识点穿起来
//volatile/cas/atomicInteger/BlockingQueue/线程交互/原子引用

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProdConsumer_BlockingQueue {

    public static void main(String[] args) throws InterruptedException {

        MyShareData myShareData = new MyShareData(new ArrayBlockingQueue<>(3));
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + "生产线程启动");
            try {
                myShareData.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + "消费线程启动");
            try {
                myShareData.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

        TimeUnit.SECONDS.sleep(5);

        myShareData.stop();

        System.out.println("大老板5秒中叫停了");

    }
}
