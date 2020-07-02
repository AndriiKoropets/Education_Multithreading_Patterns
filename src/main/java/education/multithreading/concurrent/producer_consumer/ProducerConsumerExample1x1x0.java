package education.multithreading.concurrent.producer_consumer;

public class ProducerConsumerExample1x1x0 {
    public static void main(String[] args) {
        SingleElementBuffer buffer = new SingleElementBuffer();
        new Thread(new Producer(1, 1, 1000, buffer)).start();
    }
}
