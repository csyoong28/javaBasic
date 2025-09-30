package learn.juc;

public class L12WhatReentrantSynchronized {

    // 创建一个锁对象
    static Object mylock = new Object();
    public static void main(String[] args) {
        new Thread(()->{
            // 创建第一个锁
            synchronized (mylock){
                System.out.println("这是第一层锁");
                synchronized (mylock){
                    System.out.println("这是第二层锁");
                }
            }
        }).start();
    }
}
