package com.qiangqiang.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

//        blockingQueue.add("a");
//        blockingQueue.add("b");
//        blockingQueue.add("c");
//        System.out.println(blockingQueue.element());
//        System.out.println(blockingQueue.offer("a"));
//        System.out.println(blockingQueue.offer("b"));
//        System.out.println(blockingQueue.offer("c"));
//        System.out.println(blockingQueue.offer("d"));
//
//        System.out.println(blockingQueue.peek());
//        System.out.println(blockingQueue.peek());
//        System.out.println(blockingQueue.peek());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());


//        blockingQueue.put("a");
//        blockingQueue.put("a");
//        blockingQueue.put("a");

//        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
//        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
//        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
//        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
//        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
//        System.out.println("---------------");
////        blockingQueue.put("a");
//
//        blockingQueue.take();
//        blockingQueue.take();
//        blockingQueue.take();

        BlockingQueue<String> blockingQueue1 = new SynchronousQueue<>();
        new Thread( () -> {
            try {
                System.out.println(Thread.currentThread().getName() + "put1");
                blockingQueue1.put("a");
                System.out.println(Thread.currentThread().getName() + "put2");
                blockingQueue1.put("a");
                System.out.println(Thread.currentThread().getName() + "put3");
                blockingQueue1.put("a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread( () -> {
            try {
                Thread.sleep(1000);
                blockingQueue1.take();
                Thread.sleep(1000);
                blockingQueue1.take();
                Thread.sleep(1000);
                blockingQueue1.take();
                Thread.sleep(1000);
                blockingQueue1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();

        blockingQueue1.put("a");






    }



}
