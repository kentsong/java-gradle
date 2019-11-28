package lock.demo.reentrantlock;

/**
 * 读线程
 *
 * @author chenxin
 * @since 2018-04-21
 */

public class BounedBufferRead implements Runnable {
    private BoundedBuffer buffer;
    public BounedBufferRead(BoundedBuffer boundedBuffer) {
        this.buffer = boundedBuffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(5000);
                buffer.take();
                System.out.println(Thread.currentThread().getName() + "读取成功");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}