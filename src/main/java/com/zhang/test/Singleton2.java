package com.zhang.test;

public class Singleton2 {
    /**
     * 内部类单例
     */

    private Singleton2(){}

    private static class InnerClass{
        private static Singleton2 INSTANCE = new Singleton2();
    }

    public static Singleton2 getInstance(){
        return InnerClass.INSTANCE;
    }
}
