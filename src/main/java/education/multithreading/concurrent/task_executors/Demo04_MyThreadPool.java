package education.multithreading.concurrent.task_executors;

import java.util.concurrent.Executor;

public class Demo04_MyThreadPool {

    public static void main(String[] args) {
        Executor executor = new MyThreadPool(2);
        executor.execute(getTask());
        executor.execute(getTask());
        executor.execute(getTask());
        executor.execute(getTask());
    }

    private static Runnable getTask() {
        return () -> System.out.println("Hello from " + Thread.currentThread());
    }
}
