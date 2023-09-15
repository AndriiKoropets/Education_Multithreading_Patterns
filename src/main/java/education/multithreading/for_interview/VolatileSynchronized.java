package education.multithreading.for_interview;

public class VolatileSynchronized {

    static int counter;
    static volatile boolean flag1 = false;
    static volatile boolean flag2 = false;

    static synchronized void inc() {
        counter++;
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10_000_000; i++) {
                    inc();
                }
                flag1 = true;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10_000_000; i++) {
                    inc();
                }
                flag2 = true;
            }
        }).start();

        while (!flag1 || !flag2);
        System.out.println(counter);
    }
}
