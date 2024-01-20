package com.example.javabasic;

public class C3ThreadInterrupt {
    public static void main(String[] args) {
        Baker bob = new Baker();
        bob.start();

        // Betty needs eggs - interrupt Bob
        bob.interrupt();
        int x=1;
    }
}
