package project.callnum;



import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Herman.Xiong
 * @version V1.0
 * @date 2014年11月17日 14:41:28
 * @see 服务中心
 */
public class Center extends Thread {
    private final static int MAXCOUNT = 10;

    private BlockingQueue<Waiter> waiters;
    private BlockingQueue<Customer> customers;

    private Random rand = new Random(47);

    private final static int PRODUCERSLEEPSEED = 100;
    private final static int CONSUMERSLEEPSEED = 10000;
    private boolean isClose;
    private MainListener mainListener;

    public Center(MainListener listener) {
        mainListener = listener;
        //创建10名提供服务的工作人员
        this.waiters = new LinkedBlockingQueue<Waiter>(10);
        for (int i = 0; i < MAXCOUNT; i++) {
            waiters.add(new Waiter(i+1));
        }
        //10名工作人员工作就绪,创建客户队列
        this.customers = new LinkedBlockingQueue<Customer>();
    }

    public void produce() {
        try {
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(PRODUCERSLEEPSEED));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.customers.add(new Customer());
    }

    public void consume() {
        try {

            // 服务窗口可用
            Waiter waiter = this.waiters.take();
            this.waiters.remove(waiter);

            // 客户可用
            Customer customer = this.customers.take();
            this.customers.remove(customer);

            // 窗口显示
            Log.info("waiter("+waiter.id+")"+ " 正在为 " + customer + " 服务...");
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(CONSUMERSLEEPSEED));
            Log.info(customer + " 服务结束...");
            mainListener.printInfo();

            this.waiters.add(waiter);
        } catch (InterruptedException e) {
            Log.info("---" + e.getMessage());
        }
    }

    public void setClose(boolean close) {
        isClose = close;
    }

    public boolean isClose() {
        return isClose;
    }
}