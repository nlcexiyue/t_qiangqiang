package com.qiangqiang.threadlocal;

import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
    }


}
