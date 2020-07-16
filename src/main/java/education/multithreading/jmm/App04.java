package education.multithreading.jmm;

public class App04 {
    static boolean run = true;// програма гарантовано завершиться... synchronized-with

    public static void main(String[] args) throws InterruptedException {
        Thread newThread = new Thread(() -> {
            run = false;
        });
        newThread.start();

        newThread.join();//потік main блокується допоки інший потік не виконається

        while (run);
    }
}
