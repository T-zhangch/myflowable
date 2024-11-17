package com.zhang.test;

public class Singleton1 {
    /**
     * 双重检查锁单例
     */
    private volatile static Singleton1 INSTANCE;

    public static Singleton1 getInstance(){
        if(INSTANCE == null){
            // 1.2
            synchronized(Singleton1.class){
                if(INSTANCE == null){
                    INSTANCE = new Singleton1();
                }
            }
        }
        return INSTANCE;
    }

}
