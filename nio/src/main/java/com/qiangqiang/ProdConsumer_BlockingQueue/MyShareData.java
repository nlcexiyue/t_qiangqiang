package com.qiangqiang.ProdConsumer_BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyShareData {
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    //构造注入抽象


    public MyShareData(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;

        System.out.println(blockingQueue.getClass().getName());

    }


    public void stop(){

        this.FLAG = false;
    }


    public void myProd() throws InterruptedException {
        String data = null;
        boolean retValue ;
        while (FLAG){
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName() + "插入"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName() + "插入"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println(Thread.currentThread().getName() + "大老板叫停,表示FLAG = false ,生产结束");
    }


    public void myConsumer() throws InterruptedException {
        String result = null;
        while(FLAG){
            result = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if(result == null || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "超过2秒没有取到蛋糕了,消费退出");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费队列"+result+"成功");
        }


    }
}
