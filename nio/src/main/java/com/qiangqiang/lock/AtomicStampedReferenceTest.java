package com.qiangqiang.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/21
 * \* Time: 16:45
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class AtomicStampedReferenceTest {

    public static AtomicReference<String> atomicReference = new AtomicReference<>();

    public static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,0);

    public static AtomicInteger atomicInteger = new AtomicInteger(100);

    public static void main(String[] args) {


        new Thread( () ->{
            int stamp1 = atomicStampedReference.getStamp();
            System.out.println("时间戳:"+stamp1);



            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);

            Integer reference = atomicStampedReference.getReference();
            System.out.println("引用值:" + reference);

            int stamp = atomicStampedReference.getStamp();
            System.out.println("时间戳:"+stamp);

        }).start();


        new Thread( () ->{
            atomicInteger.compareAndSet(100,1001);
            System.out.println("原子类int: " + atomicInteger.intValue());



        }).start();












        atomicReference.set("hello");

        boolean b = atomicReference.compareAndSet("hello", "hello1");
        System.out.println(b);

        String s = atomicReference.get();
        System.out.println(s);


    }








    AtomicStampedReference<Integer> ar = new AtomicStampedReference<Integer>(19,1);


}