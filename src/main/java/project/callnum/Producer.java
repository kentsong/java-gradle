package project.callnum;

/**
 * @see 生产者线程,模拟银行工作人员服务完成一位客户后开始准备服务下一位客户
 * @author Herman.Xiong
 * @date 2014年11月17日 14:55:43
 * @version V1.0
 */
public class Producer implements Runnable {
    private Center center;

    public Producer(Center center) {
        this.center = center;
    }

    @Override
    public void run() {
        while (!center.isClose() && !Thread.interrupted()) {
            //产生客户
            center.produce();
        }
    }
}