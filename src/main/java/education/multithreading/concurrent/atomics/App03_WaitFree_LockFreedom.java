package education.multithreading.concurrent.atomics;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App03_WaitFree_LockFreedom {
    private static int index = 0;
    private final static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        //not obstruction free
        new Thread(() -> {
            int myIndex = index;
            while (true) {
                label:
                while (true) {
                    if (lock.tryLock()) {
                        myIndex = index++;
                        System.out.println(myIndex);
                        break label;
                    }
                }
            }
        }).start();

        new Thread(() -> {
            int myIndex = index;
            while (true) {
                label:
                while (true) {
                    if (lock.tryLock()) {
                        myIndex = index++;
                        System.out.println(myIndex);
                        break label;
                    }
                }
            }
        }).start();

        //obstruction free
        new Thread(() -> {
            int myIndex = index;
            while (true) {
                if (lock.tryLock()) {
                    myIndex = index++;
                    System.out.println(myIndex);
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            int myIndex = index;
            while (true) {
                if (lock.tryLock()) {
                    myIndex = index++;
                    System.out.println(myIndex);
                    lock.unlock();
                }
            }
        }).start();
    }
}
