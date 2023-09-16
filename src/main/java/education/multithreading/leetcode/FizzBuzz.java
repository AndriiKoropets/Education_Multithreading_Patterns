package education.multithreading.leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class FizzBuzz {
    private int n;
    private final AtomicInteger current = new AtomicInteger(1);
    private final Lock lock = new ReentrantLock();
    private final Condition number = lock.newCondition();
    private boolean flag = false;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        lock.lock();
        try {
            while (current.get() <= n) {
                while (!flag) {
                    number.await();
                    if (current.get() > n) {
                        return;
                    }
                }
                if (current.get() % 3 == 0 && current.get() % 5 != 0) {
                    printFizz.run();
                    numberSignal();
                } else {
                    number.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        lock.lock();
        try {
            while (current.get() <= n) {
                while (!flag) {
                    number.await();
                    if (current.get() > n) {
                        return;
                    }
                }
                if (current.get() % 5 == 0 && current.get() % 3 != 0) {
                    printBuzz.run();
                    numberSignal();
                } else {
                    number.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        lock.lock();
        try {
            while (current.get() <= n) {
                while (!flag) {
                    number.await();
                    if (current.get() > n) {
                        return;
                    }
                }
                if (current.get() % 3 == 0 && current.get() % 5 == 0) {
                    printFizzBuzz.run();
                    numberSignal();
                } else {
                    number.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private void numberSignal() {
        current.getAndIncrement();
        flag = false;
        number.signalAll();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (current.get() <= n) {
                while (flag) {
                    number.await();
                    if (current.get() > n) {
                        return;
                    }
                }
                if (current.get() % 3 != 0 && current.get() % 5 != 0) {
                    printNumber.accept(current.getAndIncrement());
                    if (current.get() > n) {
                        flag = true;
                        number.signalAll();
                    }
                } else {
                    flag = true;
                    number.signalAll();
                    number.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(50);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Runnable fizz = () -> System.out.print("fizz");
        Runnable buzz = () -> System.out.print("buzz");
        Runnable fizzbuzz = () -> System.out.print("fizzbuzz");
        IntConsumer taskNumber = System.out::print;

        Runnable task1 = () -> {
            try {
                fizzBuzz.fizz(fizz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable task2 = () -> {
            try {
                fizzBuzz.buzz(buzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable task3 = () -> {
            try {
                fizzBuzz.fizzbuzz(fizzbuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable task4 = () -> {
            try {
                fizzBuzz.number(taskNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        executorService.submit(task1);
        executorService.submit(task2);
        executorService.submit(task3);
        executorService.submit(task4);

        executorService.shutdown();
    }
}

