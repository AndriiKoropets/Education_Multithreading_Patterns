package education.multithreading.jmm;

public class App02_2_volatiles {
    static int data = 0;
    static volatile boolean runA = true;
    static volatile boolean runB = true;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                boolean b = runB;
                System.out.println(data);
            }
        }).start();

        data = 1;
        runA = false;// synchronized-with не встановлюється... різні volatile змінні
    }
}
