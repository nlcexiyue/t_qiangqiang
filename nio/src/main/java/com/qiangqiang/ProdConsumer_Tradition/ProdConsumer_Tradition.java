package com.qiangqiang.ProdConsumer_Tradition;

public class ProdConsumer_Tradition {

    public static void main(String[] args) {
        //1.线程操纵资源类
        //2.判断干活的通知
        //3.防止虚假唤醒机制
        ShareData shareData = new ShareData();

        new Thread(() ->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() ->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    }
}
