package com.qiangqiang.ProdConsumer_Tradition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShareData {
    //一个初始值为零的变量,两个线程对其交替操作,一个加1一个减1,来5轮



    private int number = 0;
    private Lock lock = new ReentrantLock();

    //可以调用休眠和唤醒的方法
    private Condition condition = lock.newCondition();


    public void increment() throws Exception {
        lock.lock();

        try{
            //1.判断
            while(number != 0){
                //等待.不能生产
                condition.await();


            }

            //2.干活
            number++;
            System.out.println(Thread.currentThread().getName() + "--" + number);

            //3.通知唤醒
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }


    }



    public void decrement(){
        lock.lock();
        try{

            //1.判断
            while(number == 0){
                //等待.不能生产
                condition.await();


            }

            //2.干活
            number--;
            System.out.println(Thread.currentThread().getName() + "--" + number);

            //3.通知唤醒
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }





    }

}
