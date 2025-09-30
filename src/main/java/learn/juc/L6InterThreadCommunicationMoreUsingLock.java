package learn.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class L6InterThreadCommunicationMoreUsingLock {

    public static void main(String[] args) {

        Share3 share = new Share3();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                share.incr();
            }
        }, "AA").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                share.incr();
            }
        }, "CC").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}

class Share3 {
    // 设置临界资源
    private int number = 0;
    // 创建一个Com  可重入锁
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    // 实现+1操作
    public void incr() {
        // 上锁
        lock.lock();
        try {
            // 判断 （放在循环中，防止虚假唤醒）
            while (number != 0) {
                condition.await();
            }
            // 干活
            number++;
            System.out.print(Thread.currentThread().getName() + "::" + number + "--->");
            // 通知其他线程
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 实现-1操作
    public void decr() throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            // 判断（wait()放在循环中，防止虚假唤醒）
            while (number != 1) {
                condition.await();
            }
            // 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            // 通知其他线程
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
