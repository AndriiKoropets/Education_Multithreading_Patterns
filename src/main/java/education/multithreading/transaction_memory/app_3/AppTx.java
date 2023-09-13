package education.multithreading.transaction_memory.app_3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppTx {
    public static void main(String[] args) {
        final LatchTx latch = new LatchTx();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    latch.doOpen();
                } catch (InterruptedException ignore) {/*NOP*/}
            }
        }).start();

        ExecutorService exec = Executors.newCachedThreadPool();
        for (int k = 0; k < 5; k++) {
            exec.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    latch.await();
                    System.out.println("Hello!");
                    return null;
                }
            });
        }
    }
}
