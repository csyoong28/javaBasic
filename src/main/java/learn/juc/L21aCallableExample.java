package learn.juc;

import java.util.concurrent.*;

public class L21aCallableExample {

    // Simulates calling a slow user microservice
    public static String fetchUserDetails() throws InterruptedException {
        Thread.sleep(1000);
        return "User Details: John Doe";
    }

    // Simulates calling a slow trading microservice
    public static Double fetchAccountBalance() throws InterruptedException {
        Thread.sleep(1500); // This one is a bit slower
        return 54321.99;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<String> userTask = () -> fetchUserDetails();
        Callable<Double> balanceTask = () -> fetchAccountBalance();

        Future<String> userFuture = executor.submit(userTask);
        Future<Double> balanceFuture = executor.submit(balanceTask);

        System.out.println("Main thread has submitted tasks and is now WAITING...");

        // BLOCKING CALL! The main thread stops here until the user details are ready.
        String userDetails = userFuture.get();
        // BLOCKING CALL! The main thread stops here until the balance is ready.
        Double balance = balanceFuture.get();

        System.out.println("Main thread unblocked. Combining results.");
        System.out.println(userDetails + ", Balance: $" + balance);

        executor.shutdown();
    }
}
