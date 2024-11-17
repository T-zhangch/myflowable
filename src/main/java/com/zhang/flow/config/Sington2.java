package com.zhang.flow.config;

public class Sington2 {

    private Sington2(){}

    private static class SingleTonHoler{
        private static Sington2 INSTANCE = new Sington2();
    }

    public static Sington2 getInstance(){
        return SingleTonHoler.INSTANCE;
    }

    public static void main(String[] args) {
        Sington2 instance = Sington2.getInstance();

        new Thread(()->{
            for(int i = 0;i<100; i ++){
                System.out.println(instance == Sington2.getInstance());
            }
        }).start();

        new Thread(()->{
            for(int i = 0;i<100; i ++){
                System.out.println(instance == Sington2.getInstance());
            }
        }).start();

    }

}
