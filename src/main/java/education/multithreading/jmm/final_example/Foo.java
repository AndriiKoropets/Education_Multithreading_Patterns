package education.multithreading.jmm.final_example;

public class Foo {
    public final int a; /* always visible */

    public Foo() {
        this.a = 5;
    }
}
