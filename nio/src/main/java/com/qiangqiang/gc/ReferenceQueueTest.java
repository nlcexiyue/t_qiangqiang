package com.qiangqiang.gc;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceQueueTest {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o,referenceQueue);
        String a = "11";
        a.intern();
        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("------------------");
        o = null;
        System.gc();
        Thread.sleep(2000);

        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());


    }
}
