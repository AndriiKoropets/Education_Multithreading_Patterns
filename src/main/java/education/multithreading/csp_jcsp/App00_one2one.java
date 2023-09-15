package education.multithreading.csp_jcsp;

public class App00_one2one {
    public static void main(String[] args) {
        One2OneChannelInt c = Channel.one2oneInt();
        new Parralel(new CSProcess[] {
                new CSProcess[] {
                        new Producer(c.out(), 0),
                        new Consumer(c.in(), "dst")
                }
        })
    }
}
