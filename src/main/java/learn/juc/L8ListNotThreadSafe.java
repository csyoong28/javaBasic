package learn.juc;

import java.util.ArrayList;
import java.util.UUID;

public class L8ListNotThreadSafe {
    public static void main(String[] args) {
        // 创建ArrayList 集合
        ArrayList<String> list = new ArrayList<>();

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
