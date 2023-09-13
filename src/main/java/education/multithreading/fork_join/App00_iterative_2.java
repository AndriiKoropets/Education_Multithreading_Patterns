package education.multithreading.fork_join;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class App00_iterative_2 {

    public static void main(String[] args) throws InterruptedException {
        AtomicLong result = new AtomicLong(0);
        int cpuCount = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(cpuCount);
        final CountDownLatch latch = new CountDownLatch(100);

        for (int k = 0; k < 100; k++) {
            int finalK = k;
            pool.submit(() -> {
                //[0...10_000), [10_000, 20_000), ...
                long localResult = calc(10_000 * finalK, 10_000 * (finalK + 1));
                result.addAndGet(localResult);
                latch.countDown();
            });

        }

        latch.await();

        System.out.println(result);
        pool.shutdown();
    }

    private static long calc(int from, int to) {
        long result = 0;
        for (int index = from; index < to; index++) {
            if (index % 3 != 0 &&  index % 5 != 0) {
                result += index;
            }
        }
        return result;
    }
}
