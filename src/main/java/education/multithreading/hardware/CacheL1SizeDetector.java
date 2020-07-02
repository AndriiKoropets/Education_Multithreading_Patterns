package education.multithreading.hardware;

public class CacheL1SizeDetector {
    public static void main(String[] args) {
        byte[] array = new byte[64*1024];

        for (int testIntex = 0; testIntex < 10; testIntex++) {
            testFunction(array);
            System.out.println("-----");
        }
    }

    private static void testFunction(byte[] array) {
        for (int len = 8192; len <= array.length; len += 8192) {

            long t0 = System.nanoTime();
            for (int n = 0; n < 10; n++) {
                for (int index = 0; index < len; index += 64) {
                    array[index] = 1;
                }
            }
            long dt = System.nanoTime() - t0;
            System.out.println("len:" + len + ", dT:" + dt + ", dt*10/len = " + (dt*10)/len);

        }
    }
}
