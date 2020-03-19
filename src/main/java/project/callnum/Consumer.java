package project.callnum;

/**
 * @see 模拟客户完成业务需求后,服务人员空闲就绪
 * @author Herman.Xiong
 * @date 2014年11月17日 14:57:11
 * @version V1.0
 */
public class Consumer implements Runnable {
    private Center center;

    public Consumer(Center center) {
        this.center = center;
    }

    @Override
    public void run() {
        while (!center.isClose() && !Thread.interrupted()) {
            center.consume();
        }
    }

}
