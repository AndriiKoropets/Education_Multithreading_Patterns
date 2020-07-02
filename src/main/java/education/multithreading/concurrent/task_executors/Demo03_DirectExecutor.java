package education.multithreading.concurrent.task_executors;

import java.util.concurrent.Executor;

public class Demo03_DirectExecutor {

    public static void main(String[] args) {
        Executor executor = getExecutor();
        executor.execute(getTask());
        executor.execute(getTask());

        System.out.println("Hello from " + Thread.currentThread());
    }

    private static Executor getExecutor() {
        return Runnable::run;
    }

    private static Runnable getTask() {
        return () -> System.out.println("Hello from " + Thread.currentThread());
    }
}
