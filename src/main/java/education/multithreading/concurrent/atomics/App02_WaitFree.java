package education.multithreading.concurrent.atomics;

public class App02_WaitFree {
    private static int index = 0;
    private final static Object mutex = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            int myIndex = index;
            while (true) {
                synchronized (mutex) {
                    myIndex = index++;
                }
                System.out.println(myIndex);
            }
        }).start();

        new Thread(() -> {
            int myIndex = index;
            while (true) {
                synchronized (mutex) {
                    myIndex = index++;
                }
                System.out.println(myIndex);
            }
        }).start();
    }
}
