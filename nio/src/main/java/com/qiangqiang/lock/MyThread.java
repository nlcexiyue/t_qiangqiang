package com.qiangqiang.lock;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/19
 * \* Time: 15:30
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MyThread  extends  Thread{
    @Override
    public  void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i="+(i+1));
        }
    }
}