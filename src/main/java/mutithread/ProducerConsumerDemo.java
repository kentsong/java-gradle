package mutithread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者/消费者线程阻塞打印，线程启动 & 终止
 * @author kentsong
 */
public class ProducerConsumerDemo {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(10);
        Thread[] threadsArray = new Thread[4];

        Runnable producer = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    queue.put(new Object());
                    System.out.println("thread=" + Thread.currentThread().getName() + ", put obj");
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 在这里调了一次interrupt()，保证线程未处于阻塞状态
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("thread=" + Thread.currentThread().getName() +" 退出成功了");
        };

        threadsArray[0] = new Thread(producer);
        threadsArray[0].start();
        threadsArray[1] = new Thread(producer);
        threadsArray[1].start();

        Runnable consumer = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Object obj = queue.take();
                    System.out.println("thread=" + Thread.currentThread().getName() + ", take obj=" + obj);
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 在这里调了一次interrupt()，保证线程未处于阻塞状态
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("thread=" + Thread.currentThread().getName() +" 退出成功了");
        };
        threadsArray[2] = new Thread(consumer);
        threadsArray[2].start();
        threadsArray[3] = new Thread(consumer);
        threadsArray[3].start();


        Thread.sleep(1000 * 12);
        for(Thread thread : threadsArray){
            thread.interrupt();
        }
    }

}
