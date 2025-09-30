package learn.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class L15Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"执行Runnable");
        }).start();
        FutureTask<String> task = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + "使用Callable接口");
            return "Callable接口返回值";
        });
        new Thread(task).start();

        while(!task.isDone()) {
            System.out.println("waiting for Callable to finish...");
        }

        System.out.println("Callable返回值：" + task.get());
    }
}
