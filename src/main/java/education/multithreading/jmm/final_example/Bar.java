package education.multithreading.jmm.final_example;

public class Bar {

    public final int b; /* always visible */

    public Bar() {
        this.b = 7;
    }
}
