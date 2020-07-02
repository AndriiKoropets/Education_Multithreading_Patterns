package education.multithreading.concurrent.producer_consumer;

public class SingleElementBuffer_PrivateMutex {

    private Integer elem = null;
    private final Object lock = new Object();

        public void put(int newElem) throws InterruptedException {
            synchronized (lock) {
                while (this.elem != null) {
                    this.wait();
                }
                this.elem = newElem;
                this.notifyAll();
            }
        }

        public int get() throws InterruptedException {
            synchronized (lock) {
                while (elem == null) {
                    this.wait();
                }
                Integer result = this.elem;
                this.elem = null;
                this.notifyAll();
                return result;
            }
        }
}
