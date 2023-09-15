package education.multithreading.for_interview;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;
import lombok.SneakyThrows;

public class ZeroEvenOdd_Lock {
    private int n;
    private AtomicInteger counter;

    final Lock lock = new ReentrantLock();
    Condition zeroCondition = lock.newCondition();
    Condition evenCondition = lock.newCondition();
    Condition oddCondition = lock.newCondition();
    private volatile boolean zeroLock = true;

    public ZeroEvenOdd_Lock(int n) {
        this.n = n;
        counter = new AtomicInteger(1);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    @SneakyThrows
    public void zero(IntConsumer printNumber) {
        while (counter.get() < n) {
            lock.lock();
            while (!zeroLock){
                zeroCondition.await();
            }
            try {

                printNumber.accept(0);
                zeroLock = false;
            } finally {
                lock.unlock();
            }
        }
    }

    @SneakyThrows
    public void even(IntConsumer printNumber) {
        while (counter.get() < n) {
            lock.lock();
            while (n % 2 == 1) {
                evenCondition.await();
            };
            int validate = counter.getAndIncrement();
            if (validate % 2 == 0) {
                try {
                    lock.lock();
                    printNumber.accept(validate);
                    zeroLock = true;
                } finally {
                    zeroCondition.signalAll();
                    oddCondition.signalAll();
                    lock.unlock();
                }

            }

        }

    }

    @SneakyThrows
    public void odd(IntConsumer printNumber) {
        while (counter.get() < n) {
            lock.lock();
            while (n % 2 == 0) {
                oddCondition.await();
            };
            int validate = counter.getAndIncrement();
            if (validate % 2 == 1) {
                try {
                    lock.lock();
                    printNumber.accept(validate);
                    zeroLock = true;
                } finally {
                    zeroCondition.signalAll();
                    evenCondition.signalAll();
                    lock.unlock();
                }
            }

        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd_Lock object = new ZeroEvenOdd_Lock(100);

        IntConsumer task = System.out::print;

        new Thread(() -> object.zero(task)).start();
        new Thread(() -> object.even(task)).start();
        new Thread(() -> object.odd(task)).start();
    }
}
