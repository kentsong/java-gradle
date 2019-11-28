package lock.demo.reentrantlock;

/**
 * 写线程
 *
 * @author chenxin
 * @since 2018-04-21
 */

public class BoundedBufferWrite implements Runnable {

    private BoundedBuffer buffer;

    public BoundedBufferWrite(BoundedBuffer boundedBuffer) {
        this.buffer = boundedBuffer;
    }

    @Override

    public void run() {
        try {
            while (true) {
                Thread.sleep(3000);
                buffer.put("iengchen");
                System.out.println(Thread.currentThread().getName() + ":写入成功");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}