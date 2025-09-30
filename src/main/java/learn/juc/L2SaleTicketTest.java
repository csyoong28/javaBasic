package learn.juc;


// using synchronized
public class L2SaleTicketTest {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        //实现Runnable接口
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    ticket.sale();
                }
            }
        };
        new Thread(r, "A").start();
        new Thread(r, "B").start();
        new Thread(r, "C").start();

    }


}

class Ticket{
    private int rest = 1000;

    public synchronized void sale() {
        if (rest > 0)
            System.out.println(Thread.currentThread().getName()+"卖出一张票，还剩："+ --rest + "张；");
    }
}
