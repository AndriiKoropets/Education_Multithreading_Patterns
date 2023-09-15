package education.multithreading.for_interview;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;
    private AtomicInteger counter;

    final Object monitor = new Object();
    private volatile boolean zeroLock = true;
    private volatile boolean evenLock = false;

    public ZeroEvenOdd(int n) {
        this.n = n;
        counter = new AtomicInteger(1);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) {
        while (counter.get() < n) {
            while (!zeroLock){};
            synchronized (monitor) {
                printNumber.accept(0);
            }
            zeroLock = false;
        }
    }

    public void even(IntConsumer printNumber) {
        while (counter.get() < n) {
            while (zeroLock || !evenLock);
            int validate = counter.getAndIncrement();
            if (validate % 2 == 0) {
                synchronized (monitor) {
                    printNumber.accept(validate);
                }
            }
            zeroLock = true;
            evenLock = false;
        }

    }

    public void odd(IntConsumer printNumber) {
        while (counter.get() < n) {
            while (zeroLock || evenLock);
            int validate = counter.getAndIncrement();
            if (validate % 2 == 1) {
                synchronized (monitor) {
                    printNumber.accept(validate);
                }
            }
            zeroLock = true;
            evenLock = true;
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd object = new ZeroEvenOdd(100);

        IntConsumer task = System.out::print;

        new Thread(() -> object.zero(task)).start();
        new Thread(() -> object.even(task)).start();
        new Thread(() -> object.odd(task)).start();
    }
}

