package com.qiangqiang.lock;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/21
 * \* Time: 15:43
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class SingletonDouble {

    public static volatile SingletonDouble singletonDouble;

    private SingletonDouble(){

    }

    public static SingletonDouble getInstance(){
        if (singletonDouble != null){
            synchronized (SingletonDouble.class){
                if(singletonDouble != null){
                    singletonDouble = new SingletonDouble();
                }
            }
        }
        return singletonDouble;
    }
}