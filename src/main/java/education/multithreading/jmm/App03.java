package education.multithreading.jmm;

public class App03 {
    static boolean run = true;// програма гарантовано завершиться... synchronized-with

    public static void main(String[] args) {
        Thread newThread = new Thread(() -> {
            run = false;
        });
        newThread.start();
        while (newThread.isAlive());
        while (run);
    }
}
