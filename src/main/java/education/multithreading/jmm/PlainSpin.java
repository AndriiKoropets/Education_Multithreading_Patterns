package education.multithreading.jmm;

public class PlainSpin {

    boolean ready;

    public void actor1() {
        while (!ready); // spin
    }

    public void signal() {
        ready = true;
    }
}
