package lock.demo.reentrantlock;

/**
 * 用于测试BoundedBuffer、BoundedBufferWrite和BounedBufferRead类
 *
 * @author chenxin
 * @since 2018-04-21
 */

public class BoundedBufferTest {

    //定义写线程数量
    private static int writeNum = 3;

    //定义读线程数量
    private static int readNum = 1;

    public static void main(String[] args) {
        //所有的写线程和读线程共同操作的缓冲队列
        BoundedBuffer buffer = new BoundedBuffer();
        //启动写线程
        for (int i = 0; i < writeNum; i++) {
            new Thread(new BoundedBufferWrite(buffer), "写入线程-" + i).start();
            System.out.println("启动写线程—" + i);
        }
        //启动读线程
        for (int j = 0; j < readNum; j++) {
            new Thread(new BounedBufferRead(buffer), "读取线程-" + j).start();
            System.out.println("启动读线程-" + j);
        }
    }

}

