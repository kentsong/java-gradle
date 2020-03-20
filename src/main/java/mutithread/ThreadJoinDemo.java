package mutithread;

/**
 * @author wwj
 * 通过子程序join使线程按顺序执行
 * 主线程等待join子线程终止 ( 前提是join 线程在执行中，若已结束或未开始，则主线程不等待 )
 */
public class ThreadJoinDemo {

    public static void main(String[] args) {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("产品经理规划新需求");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    thread1.join();
                    System.out.println("开发人员开发新需求功能");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    thread2.join();
                    System.out.println("测试人员测试新功能");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("早上：");
        System.out.println("产品经理来上班了...");
        thread1.start();
        System.out.println("测试人员来上班了...");
        thread3.start();
        System.out.println("开发人员来上班了...");
        thread2.start();

    }
}