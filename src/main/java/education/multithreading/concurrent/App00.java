package education.multithreading.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class App00 {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(16);

        //Producer
        for (int k = 0; k < 3; k++) {
            final int finalK = k;
            new Thread(() -> {
                int counter = 0;
                while (true) {
                    try {
//                        queue.put(++counter);

//                        queue.add(++counter);
//                        if (!queue.offer(++counter)) {
//                            //wait
//                        }
                        Thread.sleep(300L + 1000*finalK);
                        String data = "elem-" + finalK + "/" + ++counter;
                        queue.put(data);
                        System.out.println("put: " + data);
                    } catch (InterruptedException ignore) {
                        /*NOP*/
                    }
                }
            }).start();
        }


        //Consumer
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("... wait for take");
//                    int data = queue.take(); //block thread
                    String data = queue.poll(1, TimeUnit.SECONDS);
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
