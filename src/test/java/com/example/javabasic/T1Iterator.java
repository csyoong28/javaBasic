package com.example.javabasic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.ListIterator;

@SpringBootTest
public class T1Iterator {

    @Test
    void listIterator_vs_iterator() {
        //list iterator extends iterator, more powerful allow go in backward direction
        //can do reverse with it
        List<String> list = List.of("a", "b", "c");
        ListIterator<String> listIterator = list.listIterator(list.size());
        while(listIterator.hasPrevious()) {
            System.out.printf("%s ", listIterator.previous());
        }

    }
}
