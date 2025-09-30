package learn.juc;

//虚假唤醒
public class L5InterThreadCommunicationMore {
    public static void main(String[] args) {
        Share2 share = new Share2();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        share.incr();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        share.decr();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        share.incr();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        share.decr();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

// 创建一个资源类
class Share2{
    // 设置临界资源
    private int number = 0;

    // 实现+1操作
    public synchronized void incr() throws InterruptedException {
        // 操作：判断、干活、通知
        while (number != 0) {
            // number不为0，等待
            // 哪里睡哪里起
            this.wait();
        }
        number++;
        System.out.print(Thread.currentThread().getName()+":: "+number+"--->");
        // 唤醒其他线程
        this.notifyAll();
    }

    // 实现-1操作
    public synchronized void decr() throws InterruptedException {
        // 操作：判断、干活、通知
        while (number != 1) {
            // number不为0，等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+":: "+number);
        this.notifyAll();
    }
}
