package project.callnum;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 模擬簡易的叫號系統
 */
public class CallNum {

    static ThreadPoolExecutor mExec = null;

    public static void main(String[] args) throws InterruptedException {

        //创建服务中心,如一个银行的营业厅
        Center center = new Center(mainListener);
        mExec =
                new ThreadPoolExecutor(3, 5,
                        5L, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<Runnable>(5));

//      SynchronousQueue  超过 PoolSize 会发生 java.util.concurrent.RejectedExecutionException

        //超出队列应对策略
//        AbortPolicy	直接抛出异常，该策略也为默认策略
//        CallerRunsPolicy	在调用者线程中执行该任务
//        DiscardOldestPolicy	丢弃阻塞队列最前面的任务，并执行当前任务
//        DiscardPolicy	直接丢弃任务
        mExec.setRejectedExecutionHandler(new DelaySelfPolicy());


        //模拟产生服务人员
        Producer producer = new Producer(center, 10);
        //模拟产生N多客户
//        Consumer consumer = new Consumer(center);
        mExec.execute(producer);
        //模拟10名客户
        for (int i = 0; i < 10; i++) {
            printPoolInfo(mExec);
            mExec.execute(new Consumer(center));
            Log.info("-----  exec.execute(consumer)------");
        }
        TimeUnit.SECONDS.sleep(10);
        //關閉， exec.shutdown() 會等內部 Thread 都執行完再推出程序
        center.setClose(true);
        //TODO 未知原因，主进程无法退出，因为有线程没停止
        mExec.shutdown();
//        mExec.shutdownNow();
        Log.info("-----  exec.shutdown() ------");
    }

    static void printPoolInfo(ThreadPoolExecutor exec) {
        Log.info("======Pool Info=====");
        Log.info("ActiveCount:" + exec.getActiveCount());
        Log.info("CompletedTaskCount:" + exec.getCompletedTaskCount());
        Log.info("Queue().size:" + exec.getQueue().size());
        Log.info("MaximumPoolSize():" + exec.getMaximumPoolSize());
        Log.info("===");
    }

    /**
     * 休息1秒重新尝试放入
     **/
    public static class DelaySelfPolicy implements RejectedExecutionHandler {
        public DelaySelfPolicy() {
        }

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            Log.info("======Runnable " + r + " 被拒绝了...等待一秒吧=====");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
            if (!e.isShutdown()) {
                Log.info("======Runnable " + r + " 再次尝试进入队列");
                e.execute(r);
            }
        }
    }

    private static MainListener mainListener = new MainListener(){
        @Override
        public void printInfo() {
            if(mExec.isShutdown()){
                printPoolInfo(mExec);
            }
        }
    };

}

interface MainListener {
    void printInfo();
}
