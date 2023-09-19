package education.multithreading.jmm;

public class JmmDekkerInstructionsReorderingJIT {

    private volatile static int x;
    private volatile static int y;

    private static int T1(int i) {
        x = i;
        return y;
    }

    private static int T2(int i) {
        y = i;
        return x;
    }

    public static void main(String[] args) throws Exception {
        JmmDekkerInstructionsReorderingJIT obj = new JmmDekkerInstructionsReorderingJIT();
        // invoke JIT
        for (int i = 0; i < 10000; i++) {
            int k = i;
            new Thread(() -> obj.T1(k)).start();
            new Thread(() -> obj.T2(k)).start();
            System.out.println(obj.x + " - " + obj.y);
        }
        System.out.println();
        Thread.sleep(1000);
    }
}
