package education.multithreading.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class UtilConcurrency00 {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(16);//analog for ArrayList
//        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(16);//analog for LinkedList

        //Producer
        new Thread(() -> {
            int counter = 0;
            while (true) {
                try {
                    queue.put(++counter);

                    queue.add(++counter);
                    if (!queue.offer(++counter)) {
                        //wait
                    }

                    System.out.println("put: " + counter);
                } catch (InterruptedException ignore) {
                    /*NOP*/
                }
            }
        }).start();

        //Consumer
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("... wait for take");
//                    int data = queue.take(); //block thread
                    int data = queue.take();
//                    queue.remove(); /*returns NoSuchElementException if it is not exit*/
//                    int data = queue.poll(); returns null if it is not exist
                    System.out.println("take: " + data);
                } catch (InterruptedException ignore) {
                    /*NOP*/
                }
            }
        }).start();

    }
}

