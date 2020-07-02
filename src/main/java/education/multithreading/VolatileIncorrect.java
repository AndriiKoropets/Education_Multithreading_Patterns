package education.multithreading;

public class VolatileIncorrect {

    private static volatile int counter = 0;
    private static volatile boolean finish0 = false;
    private static volatile boolean finish1 = false;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int k = 0; k < 10_000_000; k++) {
                    counter++;
                }
                finish0 = true;
            }
        }).start();
        new Thread(() -> {
            for (int k = 0; k < 10_000_000; k++) {
                counter++;
            }
            finish1 = true;
        }).start();

        while (!finish0 || !finish1);
        System.out.println(counter);
    }
}
