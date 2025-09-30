package learn.juc;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class L17aCyclicBarrier {
    public static void main(String[] args) {
        int threadCount = 4;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        // A barrier for 4 threads, with an action that runs when they all meet.
        CyclicBarrier barrier = new CyclicBarrier(threadCount,
                () -> System.out.println("--- All threads finished Phase 1. Merging data. Starting Phase 2 ---"));

        System.out.println("Starting batch processing...");
        for (int i = 0; i < threadCount; i++) {
            executor.submit(new Worker(i, barrier));
        }

        executor.shutdown();
    }
}

class Worker implements Runnable {
    private final int id;
    private final CyclicBarrier barrier;

    public Worker(int id, CyclicBarrier barrier) {
        this.id = id;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            // Phase 1: Read data from a source
            System.out.println("Worker " + id + " is reading data.");
            Thread.sleep((long) (Math.random() * 2000 + 1000));
            System.out.println("Worker " + id + " finished reading data, waiting at barrier.");
            barrier.await(); // Wait for all other workers

            // Phase 2: Process the data
            System.out.println("Worker " + id + " is calculating risk.");
            Thread.sleep((long) (Math.random() * 2000 + 1000));
            System.out.println("Worker " + id + " finished calculating risk.");
            // In a real multi-phase app, you could have another await() here.

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}