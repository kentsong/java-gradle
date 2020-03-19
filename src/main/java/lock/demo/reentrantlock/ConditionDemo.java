package lock.demo.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void method1() throws InterruptedException {
        System.out.println("method1 pre get lock");
        lock.lock();
        System.out.println("method1 get lock");
        try {
            System.out.println(Thread.currentThread().getName() + ":条件不满足，开始await");
            condition.await();
            System.out.println(Thread.currentThread().getName() + ":条件满足了，开始执行后续");
        } finally {
            lock.unlock();
        }
    }

    void method2() throws InterruptedException {
        System.out.println("method2 pre get lock");
        lock.lock();
        System.out.println("method2 get lock");
        try {
            System.out.println(Thread.currentThread().getName() + ":需要5秒的准备时间");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + ":准备完成，唤醒其他线程");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo demo = new ConditionDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        demo.method1();
    }
}
