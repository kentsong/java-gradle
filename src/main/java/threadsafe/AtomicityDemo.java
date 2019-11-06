package threadsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多線程競爭，數值加總
 * 確保保证线程安全 Demo
 */
public class AtomicityDemo {

    public static AtomicInteger count = new AtomicInteger();//原子操作
    public static volatile int countNum = 0; //volatile    只能保证可见性，不能保证原子性
    public static int synNum = 0;//同步处理计算

    public static void inc() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        //volatile 变数
        countNum++;
        //原子操作
        int c = count.addAndGet(1);
        //synchronized 操作
        add();
        System.out.println(Thread.currentThread().getName() + "------>" + c);
    }

    public static synchronized void add(){
        synNum++;
    }

    public static void main(String[] args) {
        // 1000 個線程
        int threads = 1000;
        CountDownLatch cd1 = new CountDownLatch(threads);
        for (int i = 0; i < threads; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        AtomicityDemo.inc();
                    }
                    cd1.countDown();
                }
            }).start();
        }

        try {
            cd1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName());
        System.out.println("------- 运行结果 -------- \n" +
                "atomicInteger：" + count.get() + "\n" +
                "volatile int：" + countNum + "\n" +
                "synchronized int：" + synNum);
    }
}
