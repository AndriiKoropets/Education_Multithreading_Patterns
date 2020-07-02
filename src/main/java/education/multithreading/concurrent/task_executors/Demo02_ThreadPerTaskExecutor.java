package education.multithreading.concurrent.task_executors;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

public class Demo02_ThreadPerTaskExecutor {

    public static void main(String[] args) {
        Executor executor0 = getExecutor();
        executor0.execute(getTask());
        executor0.execute(getTask());

        System.out.println("Hello from " + Thread.currentThread());
    }

    private static Executor getExecutor() {
        final AtomicLong index = new AtomicLong(0);
        final ThreadGroup group = new ThreadGroup("MyGroup");
        return command -> {
            Thread thread = new Thread(group, command);
            thread.setName("Thread#" + index.incrementAndGet());
            thread.setDaemon(true);
            thread.start();
        };
    }

    private static Runnable getTask() {
        return () -> {
            System.out.println("Hello from " + Thread.currentThread() + " is deamon = " + Thread.currentThread().isDaemon());
        };
    }
}
