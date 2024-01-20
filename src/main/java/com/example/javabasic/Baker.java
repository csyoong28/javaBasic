package com.example.javabasic;

public class Baker extends Thread {

    public void run() {
        try {
            kneadDough();
        } catch (InterruptedException e) {
            // thread was interrupted during kneading
            buyEggs();
        }
    }

    public void kneadDough() throws InterruptedException {
        while (true) {
            // check interrupted status
            System.out.println("kneadDough");
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException("Interrupted!");
            }
            // knead dough
        }
    }

    public void buyEggs() {
        System.out.println("Buying more eggs!");
        // clear interrupted status
        Thread.interrupted();
    }

}
