package learn.threadLocal;

//without threadLocal, the thread may retrieve another's thread result.
public class MyDemo02WithSynchronized {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) {
        MyDemo02WithSynchronized myDemo1 = new MyDemo02WithSynchronized();
        for (int i = 0; i < 20; i++) {
            Thread thread =new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (MyDemo02WithSynchronized.class){
                        //每个线程：存一个变量，过一会取出这个变量
                        myDemo1.setContent(Thread.currentThread().getName()+"的数据");
                        System.out.println("----------------");
                        System.out.println(Thread.currentThread().getName()+"--->" + myDemo1.getContent());
                    }
                }
            });
            thread.setName("线程"+i);
            thread.start();
        }
    }
}
