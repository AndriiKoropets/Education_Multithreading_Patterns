package education.multithreading.jmm;

public class VolatileMemoryBarrierJIT {

    private static int field1;
    private volatile static int field2;

    private static void write(int i) {
        field1 = i << 1;
        /* StoreStore */
        field2 = i << 2;
        /* StoreLoad */
    }

    private static void read() {
        int r1 = field2;
        /* LoadLoad + LoadStore */
        int r2 = field1;
    }

    public static void main(String[] args) throws Exception {
        // invoke JIT
        for (int i = 0; i < 10000; i++) {
            write(i);
            read();
        }
        Thread.sleep(1000);
    }
}
