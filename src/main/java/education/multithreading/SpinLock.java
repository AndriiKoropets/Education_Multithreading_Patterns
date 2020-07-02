package education.multithreading;

public class SpinLock {

    static volatile boolean flag1;
    static volatile boolean flag2;

    public static void main(String[] args) throws InterruptedException {
        Object monitor = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                    System.out.println("X1");
                    flag1 = true;
                    try {
                        System.out.println("Temp1");
                        monitor.wait();
                        System.out.println("Y1");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    try {
//                        Thread.sleep(Long.MAX_VALUE);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (monitor) {
                System.out.println("X2");
                flag2 = true;
                try {

                    System.out.println("Temp2");
                    monitor.wait();
                    System.out.println("Y2");
                    monitor.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("A");
        while (!flag1 || !flag2);
        System.out.println("B");
        synchronized (monitor) {
            System.out.println("C");
            monitor.notifyAll();
            monitor.wait();
            System.out.println("D");
//            while (true);
        }
        System.out.println("E");
    }
}
