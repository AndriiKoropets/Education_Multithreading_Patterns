package education.multithreading.csp_jcsp;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInputInt;
public class Consumer implements CSProcess {
    private final ChannelInputInt in;
    private String name;

    public Consumer(ChannelInputInt in, String name) {
        this.in = in;
        this.name = name;
    }

    public void run() {
        while (true) {
            System.out.println(name + ": " + in.read());
        }
    }
}
