package education.multithreading.for_interview;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class FizzBuzz {

    private int n;
    private AtomicInteger counter;
    Lock lock = new ReentrantLock();
    Condition numberCondition = lock.newCondition();

    public FizzBuzz(int n) {
        this.n = n;
        counter = new AtomicInteger(1);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (counter.get() < n) {
            if (counter.get() % 3 == 0) {
                try {
                    lock.lock();
                    printFizz.run();
                    counter.incrementAndGet();
                } finally {
                    lock.unlock();
                    numberCondition.signalAll();
                }

            }
        }

    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (counter.get() < n) {
            if (counter.get() % 5 == 0) {
                printBuzz.run();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (counter.get() < n) {
            if (counter.get() % 3 == 0 && counter.get() % 5 == 0) {
                printFizzBuzz.run();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {

    }

    public static void main(String[] args) {
        FizzBuzz object = new FizzBuzz(100);

        Runnable task1 = () -> System.out.println("fizz");
        Runnable task2 = () -> System.out.println("buzz");
        Runnable task3 = () -> System.out.println("fizzbuzz");
        IntConsumer task4 = System.out::print;

        new Thread(() -> {
            try {
                object.fizz(task1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                object.buzz(task2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                object.fizzbuzz(task3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                object.number(task4);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
