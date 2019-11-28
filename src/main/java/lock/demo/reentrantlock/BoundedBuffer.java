package lock.demo.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenxin
 * @since 2018-04-21
 */

public class BoundedBuffer {
    //锁对象,作为类属性，保证所有方法共用一把锁
    final Lock lock = new ReentrantLock();
    //写线程条件
    final Condition notFull = lock.newCondition();
    //读线程条件
    final Condition notEmpty = lock.newCondition();

    // 缓存队列
    final Object[] items = new Object[10];
    int putptr = 0;
    int takeptr = 0;
    int count = 0;

    public void put(Object x) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"等待获取锁");
        lock.lock();
        System.out.println(Thread.currentThread().getName()+"获取锁");
        try {
            while (count == items.length) {
                //如果队列满了阻塞写线程.写线程会释放lock,写线程接收到signal时需要重新获得lock
                System.out.println(Thread.currentThread().getName()+"put满了，等待 notFull signal");
                notFull.await();
            }
            //赋值
            items[putptr] = x;
            if (++putptr == items.length)
                //如果写索引写到队列的最后一个位置了，那么重置为0,开启下一轮
                putptr = 0;
            //队列中已存在的元素个数加1
            ++count;
            //发出非空signal,等待非空signal的线程会被唤醒
            notEmpty.signal();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放锁");
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                //如果队列为空,等待非空signal.释放lock,接收到signal后需要先获得lock
                notEmpty.await();
            Object x = items[takeptr];// 取值
            if (++takeptr == items.length)
                //如果读索引读到队列的最后一个位置了，那么重置为0
                takeptr = 0;
            //队列中已存在的元素个数减1
            --count;//
            //发出condition队列不满signal,等待该signal的线程继续执行.
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }

}
