package education.multithreading.hardware;

public class CacheLineSize {
    final static int ARRAY_SIZE = 16 *1024 * 1024;

    public static void main(String[] args) {
        byte[] array = new byte[ARRAY_SIZE];
        for (int testIndex = 0; testIndex < 10; testIndex++) {
            testMethod(array);
            System.out.println("-----");
        }
    }

    private static void testMethod(byte[] array) {
        for (int stepSize = 4; stepSize <= 512; stepSize *= 2) {
            int sum = 0;

            long t0 = System.nanoTime();
            for (int i = 0; i < 100; i++) {
                for (int k = 0; k < array.length; k += stepSize) {
                    sum += array[k];
                }
            }
            long t1 = System.nanoTime();

            if (sum > 0) {
                throw new Error();
            }
            int step_count = ARRAY_SIZE / stepSize;
            System.out.println(stepSize + ". dt / step_count: " + (t1- t0)/ step_count);
        }

    }

    private static void printlnTime(long arg) {

    }
}
