package com.example.javabasic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.ListIterator;

@SpringBootTest
@Slf4j
public class T2ExceptionHandling {

    private static final Logger logger = LoggerFactory.getLogger(T2ExceptionHandling.class);

    @Test
    void logExceptionIntoFile() {
        try {
            // Your code that may throw an exception
            throw new RuntimeException("Example Exception");
        } catch (Exception e) {
            // Log the exception stack trace
            logger.error("Exception occurred with loggerFactory", e); // will be at log file
            log.error("Exception occurred with @slf4j", e);  // will be at log file
            e.printStackTrace();  //will not be at log file
        }

    }
}
