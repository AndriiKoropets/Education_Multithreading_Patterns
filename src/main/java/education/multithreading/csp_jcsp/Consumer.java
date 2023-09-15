package education.multithreading.csp_jcsp;

import org.jcsp.lang.*;
public class Consumer implements CSProcess {
    private final ChannerInputInt in;
    private String name;

    public Consumer(ChannerInputInt in, String name) {
        this.in = in;
        this.name = name;
    }

    public void run() {
        while (true) {
            System.out.println(name + ": " + in.read());
        }
    }
}
