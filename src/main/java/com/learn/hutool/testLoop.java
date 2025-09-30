package com.learn.hutool;

import java.util.Arrays;

public class testLoop {
    public static void main(String[] args) {
        //loop();
        String[] strings = new String[]{"1","2","3"};
        int count = Arrays.stream(strings)
                .map(x -> Integer.parseInt(x))
                .reduce((x1,x2) -> x1 + x2).orElse(0);
        System.out.println("Sum of integers: " + count);


    }

    private static void loop() {
        int a = 0;
        // Example of a simple loop
        for (int i = 0; i < 10; i++) {
            a = i * i;
            System.out.println("Loop iteration: " + i);
        }
    }

}
