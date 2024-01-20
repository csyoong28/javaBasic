package com.example.javabasic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
@Slf4j
public class T3Variable {

    private static final Logger logger = LoggerFactory.getLogger(T3Variable.class);

    @Test
    void useBigDecimal_than_double_or_float() {
        double result = 0.1 + 0.2;
        System.out.println("Using double: " + result);  // Prints 0.30000000000000004

        BigDecimal bdResult = new BigDecimal("0.1").add(new BigDecimal("0.2"));
        System.out.println("Using BigDecimal: " + bdResult);  // Prints 0.3

    }
}
