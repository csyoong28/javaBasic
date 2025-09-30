package learn.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//can custom the sequence this way
public class L7CustomInterThreadCommunication {
    public static void main(String[] args) {
        Share7 share = new Share7();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    share.Aprint(i);
                }

            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    share.Bprint(i);
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    share.Cprint(i);
                }

            }
        },"C").start();
    }
}

class Share7{
    private int flag = 1;

    private Lock lock = new ReentrantLock();
    // 创建三个Comdition对象，为了定向唤醒相乘
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    public void Aprint(int loop) {
        //上锁
        lock.lock();
        try{
            // 判断
            while(flag!=1) {
                c1.await();
            }
            // 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " ::本次第" + i + "次打印，是第" + loop+ "次循环");
            }
            flag = 2; //修改标志位，定向唤醒 线程b
            // 唤醒
            c2.signal();// 定向唤醒
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 解锁
            lock.unlock();
        }
    }
    public void Bprint(int loop) {
        //上锁
        lock.lock();
        try{
            // 判断
            while(flag!=2) {
                c2.await();
            }
            // 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " ::本次第" + i + "次打印，是第" + loop+ "次循环");
            }
            flag = 3; //修改标志位，定向唤醒 线程b
            // 唤醒
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 解锁
            lock.unlock();
        }
    }

    public void Cprint(int loop) {
        //上锁
        lock.lock();
        try{
            // 判断
            while(flag!=3) {
                c3.await();
            }
            // 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + " ::本次第" + i + "次打印，是第" + loop+ "次循环");
            }
            flag = 1; //修改标志位，定向唤醒 线程b
            // 唤醒
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 解锁
            lock.unlock();
        }
    }
}