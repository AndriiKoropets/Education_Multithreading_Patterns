package education.multithreading.csp_jcsp;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;

public class App00_one2one {
    public static void main(String[] args) {
        One2OneChannelInt c = Channel.one2oneInt();
//        new Parallel(new CSProcess[] {
//                new CSProcess[] {
//                        new Producer(c.out(), 0),
//                        new Consumer(c.in(), "dst")
//                }
//        });
    }
}
