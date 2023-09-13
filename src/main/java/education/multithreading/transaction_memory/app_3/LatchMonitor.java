package education.multithreading.transaction_memory.app_3;

//java.util.concurrent.CountDownLatch
public class LatchMonitor {
    private boolean open = false;

    public synchronized void doOpen() {
        open = true;
        this.notifyAll();
    }

    public synchronized void await() throws InterruptedException {
        while (!open) { //spirious wake up
            this.wait();
        }
    }
}
