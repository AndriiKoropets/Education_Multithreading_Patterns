package education.multithreading.jmm.final_example;

public class FinalFieldExample {

    private DataHolder instance;

    public void writer() {
        instance = new DataHolder();
    }

    public void reader() {
        DataHolder instance = this.instance; /* data race */
        /* LoadLoad */
        if (instance != null) {
            Foo foo = instance.foo; /* guaranteed to see non-null reference */
            int a = foo.a; /* guaranteed to see 5 */

            int c = instance.c; /* guaranteed to see 9 */

            Bar bar = instance.bar; /* no guarantee - may be null */
            if (bar != null) {
                int b = bar.b; /* guaranteed to see 7 */
            }

            int d = instance.d; /* no guarantee - may be 0 (default value) */
        }
    }
}