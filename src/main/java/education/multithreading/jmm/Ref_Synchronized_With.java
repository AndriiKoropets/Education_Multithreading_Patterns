package education.multithreading.jmm;

public class Ref_Synchronized_With {
    static boolean run = true;
    static Object ref = new Object();
    //synchronized ігнорує кеши

    public static void main(String[] args) throws InterruptedException {
        Thread newThread = new Thread(() -> {
            synchronized (ref) {
                run = false;
            }
        });
        newThread.start();
        while (true) {
            synchronized (ref) {
                System.out.println(run);
            }
        }
    }
}
