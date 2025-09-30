package learn.juc;

import java.util.concurrent.locks.ReentrantLock;

public class L13WhatReentrantLock {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(()->{
            // 上锁
            lock.lock();
            try {
                System.out.println("这是第一层锁");
                // 再次上锁
                lock.lock();
                try{
                    System.out.println("这是第二层锁");
                }finally {
                    lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        }).start();
    }
}
