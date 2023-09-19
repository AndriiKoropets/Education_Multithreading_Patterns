package education.multithreading.jmm;

public class JmmReorderingObjectExample {
    private Foo instance;

    private static class Foo {
        private int x;

        Foo() {
            this.x = 5; /* W1 */
        }
    }

    public void writer() {
        instance = new Foo(); /* W2, non-safe publish */
    }

    public void reader() {
        Foo r1 = instance; /* R1 */
        if (r1 != null) {
            int r2 = r1.x; /* R2: may be default value (0) */
        }
    }
}
