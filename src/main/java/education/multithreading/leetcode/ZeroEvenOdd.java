package education.multithreading.leetcode;

import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;

    final Object monitor = new Object();
    private volatile boolean zeroLock = true;
    private volatile boolean evenLock = false;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) {
        for (int i = 0; i < n; i++) {
            while (!zeroLock);
            synchronized (monitor) {
                printNumber.accept(0);
            }
            zeroLock = false;
        }
    }

    public void even(IntConsumer printNumber) {
        for (int i = 0; i < n; i++) {
            while (zeroLock || !evenLock);
            int validate = i + 1;
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
        for (int i = 0; i < n; i++) {
            while (zeroLock || evenLock);
            int validate = i + 1;
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
        ZeroEvenOdd object = new ZeroEvenOdd(5);

        IntConsumer task = System.out::println;

        new Thread(() -> object.zero(task)).start();
        new Thread(() -> object.even(task)).start();
        new Thread(() -> object.odd(task)).start();
    }
}
