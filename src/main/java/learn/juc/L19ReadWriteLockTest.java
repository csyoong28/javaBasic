package learn.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class L19ReadWriteLockTest {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 6; i++) {
            final int num = i;
            new Thread(()->{
                myCache.put(String.valueOf(num),String.valueOf(num));
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <= 6; i++) {
            final int num = i;
            new Thread(()->{
                myCache.get(String.valueOf(num));
            },String.valueOf(i)).start();
        }
    }
}

class MyCache{
    // 需要模仿从Map中取对象，所以先穿件一个map对象
    private volatile Map<String, Object> map = new HashMap<>();

    // 创建读写锁
    private ReadWriteLock rwlock = new ReentrantReadWriteLock();

    // 放数据
    public void put(String key, Object value) {
        // 添加写锁
        rwlock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"正在写操作"+key);
            // 暂停一会
            TimeUnit.MICROSECONDS.sleep(300);
            // 放数据
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"写完了"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放写锁
            rwlock.writeLock().unlock();
        }
    }

    // 取数据
    public void get(String key) {
        // 添加读锁
        rwlock.readLock().lock();;
        try {
            System.out.println(Thread.currentThread().getName()+"正在取操作"+key);
            // 暂停一会
            TimeUnit.MICROSECONDS.sleep(300);
            // 放数据
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"取完了"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放读锁
            rwlock.readLock().unlock();
        }
    }
}
