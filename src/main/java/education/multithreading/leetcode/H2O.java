package education.multithreading.leetcode;

import education.patterns.singleton.A;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class H2O {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    AtomicInteger counter = new AtomicInteger(2);


    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        try {
            while (counter.get() == 0) {
                condition.await();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            counter.getAndDecrement();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        lock.lock();
        try {
            while (counter.get() != 0) {
                condition.await();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            counter.set(2);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        H2O obj = new H2O();
        Runnable taskHydrogen = () -> System.out.print("H");
        Runnable taskOxygen = () -> System.out.print("O");

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future> futures = new ArrayList<>(3);
        for (int i = 0; i < 20; i++) {
            futures.add(executorService.submit(() -> {
                try {
                    obj.hydrogen(taskHydrogen);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
            futures.add(executorService.submit(() -> {
                try {
                    obj.hydrogen(taskHydrogen);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
            futures.add(executorService.submit(() -> {
                try {
                    obj.oxygen(taskOxygen);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));

            for (Future future : futures) {
                future.get();
            }
            futures.clear();
        }
//        executorService.submit(() -> {
//            try {
//                obj.hydrogen(taskHydrogen);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        executorService.submit(() -> {
//            try {
//                obj.hydrogen(taskHydrogen);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        executorService.submit(() -> {
//            try {
//                obj.oxygen(taskOxygen);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

//        ThreadPoolExecutor hydrogenPool = new ThreadPoolExecutor(2L);

        String water = "OOHHHH";
//        for (ch)
        executorService.shutdown();
    }
}
