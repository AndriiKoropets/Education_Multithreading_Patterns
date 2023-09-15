package education.multithreading.leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;
    Lock lock = new ReentrantLock();
    Condition odd = lock.newCondition();
    Condition zero = lock.newCondition();
    AtomicInteger counter = new AtomicInteger(1);
    boolean evenFlag = false;
    boolean zeroFlag = false;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (counter.get() <= n) {
                while (zeroFlag) {
                    zero.await();
                }
                if (counter.get() <= n) {
                    printNumber.accept(0);
                }
                zeroFlag = true;
                zero.signalAll();
            }
        } finally {
            lock.unlock();
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (counter.get() <= n) {
                while (!zeroFlag) {
                    zero.await();
                }
                while (!evenFlag) {
                    odd.await();
                }
                if (counter.get() % 2 == 0 && counter.get() <= n) {
                    printNumber.accept(counter.getAndIncrement());
                }
                zeroFlag = false;
                zero.signal();
                evenFlag = false;
                odd.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (counter.get() <= n) {
                while (!zeroFlag) {
                    zero.await();
                }
                while (evenFlag) {
                    odd.await();
                }
                if (counter.get() % 2 == 1 && counter.get() <= n) {
                    printNumber.accept(counter.getAndIncrement());
                }
                zeroFlag = false;
                zero.signal();
                evenFlag = true;
                odd.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOdd obj = new ZeroEvenOdd(100);
        IntConsumer task = System.out::print;
        Runnable task1 = () -> {
            try {
                obj.zero(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable task2 = () -> {
            try {
                obj.even(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable task3 = () -> {
            try {
                obj.odd(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(task1);
        executorService.submit(task2);
        executorService.submit(task3);

        executorService.shutdown();
    }
}
