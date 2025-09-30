package learn.juc;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class L24CompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 异步调用没有返回值
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+" : CompletableFuture");
        });
        completableFuture.get();

        // 异步调用
        // mq消息队列
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+" : CompletableFuture1");
            // 模拟异常
            int i = 10/0;
            return 1024;
        });
        // 完成之后调用
        completableFuture1.whenComplete((t,u)->{
            System.out.println("-----t:"+t);    // 方法的返回值
            System.out.println("-----u:"+u);    // 异常的返回信息
        }).get();
    }
}