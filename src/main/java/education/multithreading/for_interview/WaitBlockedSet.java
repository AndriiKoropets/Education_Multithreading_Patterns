package education.multithreading.for_interview;

import lombok.SneakyThrows;

public class WaitBlockedSet {
    private static volatile boolean in = false;
    private static Object monitor = new Object();
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (monitor) {
                    in = true;
                    System.out.println("X");
                    monitor.wait();
                    System.out.println("Y");
                }

            }
        }).start();

        System.out.println("A");
        while (!in) {}
        System.out.println("B");
        synchronized (monitor) {
            System.out.println("C");
            monitor.notify();
            System.out.println("D");
        }
        System.out.println("E");
    }
}
