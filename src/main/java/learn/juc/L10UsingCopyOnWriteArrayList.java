package learn.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class L10UsingCopyOnWriteArrayList {
    public static void main(String[] args) {
        // 创建ArrayList 集合
        List<String> list = new CopyOnWriteArrayList<>();

        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        // 创建10个线程，往 list 中添加元素
        for (int i = 0; i < 300; i++) {
            new Thread(()->{
                // 向集合中添加内容
                list.add(UUID.randomUUID().toString().substring(0,8));
                // 从集合中取出内容
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
