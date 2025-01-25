package learn.threadLocal;

//without threadLocal, the thread may retrieve another's thread result.
public class MyDemo01WiththreadLocal {
    ThreadLocal<String> t1 = new ThreadLocal<>();
    public String getContent() {
        return t1.get();
    }

    public void setContent(String content) {
        t1.set(content);
    }

    public static void main(String[] args) {
        MyDemo01WiththreadLocal demo = new MyDemo01WiththreadLocal();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.setContent(Thread.currentThread().getName() + " content");
                    System.out.println(Thread.currentThread().getName() + "-->" + demo.getContent());
                }
            });
            thread.setName("线程" + i);
            thread.start();
        }
    }
}
