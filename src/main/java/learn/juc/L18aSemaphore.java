package learn.juc;


import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;
        import java.util.concurrent.Semaphore;
        import java.util.concurrent.TimeUnit;

public class L18aSemaphore {

    // Our "API" can only handle 3 concurrent connections.
    private final Semaphore apiLimiter = new Semaphore(3);

    public void createTradingAccount(int userId) {
        try {
            // 1. Acquire a permit. Blocks if none are available.
            apiLimiter.acquire();
            System.out.println("✅ Permit acquired for User " + userId + ". Creating account... [Active: " + (3 - apiLimiter.availablePermits()) + "]");

            // 2. Simulate the actual API call (the critical section)
            Thread.sleep(2000);
            System.out.println("   -> Account created for User " + userId + ".");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            // 3. CRITICAL: Release the permit in a 'finally' block.
            // This ensures the permit is returned even if the API call throws an exception.
            apiLimiter.release();
            System.out.println("❌ Permit released by User " + userId + ". [Active: " + (3 - apiLimiter.availablePermits()) + "]");
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        L18aSemaphore limiter = new L18aSemaphore();

        // Simulate 10 concurrent requests
        for (int i = 1; i <= 10; i++) {
            final int userId = i;
            executor.submit(() -> limiter.createTradingAccount(userId));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}