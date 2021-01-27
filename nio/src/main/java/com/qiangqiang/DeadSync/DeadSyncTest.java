package com.qiangqiang.DeadSync;

public class DeadSyncTest {

    public static void main(String[] args) {

        Object A = new Object();
        Object B = new Object();

        new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {

                    System.out.println("获取B");
                }

            }
        }, "A").start();

        new Thread(() -> {
            synchronized (B) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {

                    System.out.println("获取A");
                }

            }
        }, "A").start();


    }
}
