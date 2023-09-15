package education.multithreading.csp_jcsp;

import org.jcsp.lan.CSTimer;
import org.jcsp.lan.ChannelOutputInt;


public class Producer implements CSprocess{
    private final ChannelOutputInt out;
    private int k;

    public Producer(ChannelOutputInt out, int k) {
        this.out = out;
        this.k = k;
    }

    public void run() {
        CSTimer timer = new CSTimer();
        while (true) {
            out.write(k++);
            timer.sleep(250);
        }
    }
}
