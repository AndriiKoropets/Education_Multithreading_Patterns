package education.multithreading.jmm.final_example;


public class DataHolder {

    public final Foo foo; /* always visible */
    public final int c; /* always visible */
    public Bar bar; /* may not be visible */
    public int d; /* may not be visible */

    public DataHolder() {
        this.foo = new Foo();
        this.bar = new Bar();
        this.c = 9;
        this.d = 10;
        /* StoreStore */
    }
}
