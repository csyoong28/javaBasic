package learn.juc;

public class L1MyThread {
    public static void main(String[] args) {
        //使用Lambda 表达式实现这个接口,创建 线程t1
        Thread t1 = new Thread(() -> {
            //判断是否是守护线程，（后台运行的）
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {
                //主线程结束，程序还在运行，jvm 没停止
            }
        }, "t1");
        // 把他设置为守护线程 ，主线程结束这个程序没有用户线程了，结束了
        t1.setDaemon(false);
        //启动线程
        t1.start();
        System.out.println(Thread.currentThread().getName() +"结束");
    }
}
