package learn.juc;

import java.util.concurrent.locks.ReentrantLock;

public class L11FairLock {
    public static void main(String[] args) {
        LTicket11 ticket = new LTicket11();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    ticket.sale();
                }
            }
        };
        new Thread(r, "A").start();
        new Thread(r, "B").start();
        new Thread(r, "C").start();

    }
}

class LTicket11{
    private int rest = 100;

    // 创建可重入锁
    private final ReentrantLock lock = new ReentrantLock(true);

    public synchronized void sale() {
        //由于要防止上锁后出现异常导致无法解锁，所以使用try-catch-finally结构来解决
        try {
            //上锁
            lock.lock();

            if (rest > 0)
                System.out.println(Thread.currentThread().getName() + "卖出一张票，还剩：" + --rest + "张；");
        }finally {
            //解锁
            lock.unlock();
        }
    }
}