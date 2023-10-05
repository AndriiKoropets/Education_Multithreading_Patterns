package education.multithreading.leetcode;

public class MemoryReorderingExample {
    private int x;
    private boolean initialized = false;

    public void writer() {
        x = 5; /* W1 */
        initialized = true; /* W2 */
    }

    public void reader() {
        boolean r1 = initialized; /* R1 */
        if (r1) {
            int r2 = x; /* R2, may read default value (0) */
        }
    }
}
