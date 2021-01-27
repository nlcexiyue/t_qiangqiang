package com.qiangqiang.gc;

import java.lang.ref.SoftReference;

public class SoftReferenceTest {

    public static void softReference_Enough(){
        Object o = new Object();
        SoftReference<Object> stringSoftReference = new SoftReference<>(o);
        System.out.println(o);
        System.out.println(stringSoftReference.get());


        o = null;
        System.gc();

        System.out.println(o);
        System.out.println(stringSoftReference.get());
    }

    public static void softReference_NotEnough(){
        Object o = new Object();
        SoftReference<Object> stringSoftReference = new SoftReference<>(o);
        System.out.println(o);
        System.out.println(stringSoftReference.get());


        o = null;
//        System.gc();

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(o);
            System.out.println(stringSoftReference.get());
        }
    }

    public static void main(String[] args) {

        softReference_NotEnough();
    }
}
