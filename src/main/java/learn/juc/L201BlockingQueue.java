package learn.juc;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class L201BlockingQueue {
    public static void main(String[] args) {
        // 创建阻塞队列
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        // 当队列中加元素
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // 检查
        System.out.println(blockingQueue.element());
        /** 此时输出的结果为
         * true
         * true
         * true
         * a
         * */
        System.out.println(blockingQueue.add("d"));
        // 此时再添加元素，会抛出异常:IllegalStateException: Queue full
        // 取出元素
        System.out.println(blockingQueue.remove());
        // 检查
        System.out.println(blockingQueue.element());
        /** 此时输出的结果为
         * true
         * true
         * true
         * a
         * a (取出数据)
         * b (队首数据)
         * */
    }
}