package learn.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class L22ThreadPoolExecutor {
    public static void main(String[] args) {
        // 组定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                // 常驻线程数量（核心）2个
                2,
                // 最大线程数量5个
                5,
                // 线程存活时间:2秒
                2L,
                TimeUnit.SECONDS,
                // 阻塞队列
                new ArrayBlockingQueue<>(3),
                // 默认线程工厂
                Executors.defaultThreadFactory(),
                // 拒绝策略。抛出异常
                new ThreadPoolExecutor.AbortPolicy()
        );

        try{
            for (int i = 1; i <= 80; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 关闭线程池
            threadPool.shutdown();
        }
    }
}

