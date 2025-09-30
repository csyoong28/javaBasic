package learn.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class L21ThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        // 是个顾客请求
        try {
            for (int i = 1; i <= 100; i++) {
                // 到此时执行execute()方法才创建线程
                threadPool1.execute(() -> {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
                System.out.println("task " + i + " 已提交");
            }
        } finally {
            // 关闭线程
            threadPool1.shutdown();
        }
    }
}

