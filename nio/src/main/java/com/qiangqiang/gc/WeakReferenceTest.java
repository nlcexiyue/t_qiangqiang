package com.qiangqiang.gc;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {

    public static void main(String[] args) {

        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o);
        System.out.println(o);
        System.out.println(weakReference.get());
        o = null;
        System.gc();
        System.out.println(o);
        System.out.println(weakReference.get());

    }
}
