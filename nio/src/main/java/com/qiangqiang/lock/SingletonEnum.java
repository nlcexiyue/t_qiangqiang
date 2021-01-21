package com.qiangqiang.lock;

public enum SingletonEnum {
    INSTANCE;
    int value;

    //这里我们可以自定义构造函数
    private SingletonEnum(){
        value = 1;
        System.out.println("INSTANCE现在创建了");
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
