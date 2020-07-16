package education.multithreading.jmm;

public class App01 {
    static int run = 0;

    public static void main(String[] args) {
        run = 1;
        new Thread(() -> {
            System.out.println(run);//гарантовано надрукує 1
        }).start();
    }
}
