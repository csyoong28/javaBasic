package learn.juc;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class L23ForkJoinPool extends RecursiveTask<Integer> {

    // 拆分差值不能超过10
    private static final Integer VALUE  = 10;
    private int begin;
    private int end;
    private int result;

    public L23ForkJoinPool(int begin, int end){
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        // 判断两个值的差值是否大于10
        if((end-begin)<=10) {
            // 相加操作
            for (int i = begin; i <= end ; i++) {
                result = result + i;
            }
        } else {
            // 大于10 继续拆分
            int middle = (begin + end) / 2;
            // 拆分左边
            L23ForkJoinPool task01 = new L23ForkJoinPool(begin, middle);
            // 拆分右边
            L23ForkJoinPool task02 = new L23ForkJoinPool(middle + 1, end);
            // 调用方法拆分
            task01.fork();
            task02.fork();
            // 合并结果
            result = task01.join() + task02.join();
        }
        return result;
    }
}

class ForkJoinTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建MyTask对象
        L23ForkJoinPool task = new L23ForkJoinPool(0, 100);
        // 创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(task);

        // 获取最终合并之后结果
        Integer reslut = forkJoinTask.get();
        System.out.println(reslut);
        forkJoinPool.shutdown();
    }
}