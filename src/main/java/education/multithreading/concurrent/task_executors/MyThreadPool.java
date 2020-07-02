package education.multithreading.concurrent.task_executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public class MyThreadPool implements Executor {

    private final BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(1);
    private final Thread[] pool;

    public MyThreadPool(int threadCounter) {
        this.pool = new Thread[threadCounter];
        for (int k = 0; k < threadCounter; k++) {
            pool[k] = new Thread(() -> {
                while (true) {
                    try {
                        Runnable nextTask = taskQueue.take();

                        nextTask.run();
                    } catch (InterruptedException ignore) {break;}
                }
            });
            pool[k].start();
        }
    }

    @Override
    public void execute(Runnable command) {
        if (!taskQueue.offer(command)) {
            System.out.println("Rejected");
        }

        try {
            taskQueue.put(command);
        } catch (InterruptedException e) { throw new Error("Never", e); }
    }
}
