package com.example.javabasic;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class C5ThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int poolSize = 2;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);

        // Submit a task that throws an exception
        executorService.execute(() -> {
            System.out.println("Task started");
           // Thread.sleep(5000);

            throw new RuntimeException("Simulated exception");
        });
        //Void unused = future.get();

        // Continue with other tasks or shutdown the executor without calling get()
        int x = 1;
        System.out.println("print: "+x);
        // Shut down the executor
        executorService.shutdown();
    }
}
