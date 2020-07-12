package education.multithreading.concurrent.atomics;

import java.util.concurrent.atomic.AtomicInteger;

public class App04_Atomic {

    private static AtomicInteger index = new AtomicInteger(0);

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                cas:
                while (true) {
                    int oldValue = index.get();
                    int newValue = oldValue + 1;
                    if (index.compareAndSet(oldValue, newValue)) {
                        break cas;
                    }
                }
                System.out.println(index.incrementAndGet());
            }
        }).start();

        new Thread(() -> {
            while (true) {
                System.out.println(index.incrementAndGet());
            }
        }).start();
    }
}
