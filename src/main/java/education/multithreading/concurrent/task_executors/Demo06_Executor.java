package education.multithreading.concurrent.task_executors;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Demo06_Executor {

    public static void main(String[] args) {
//        Executor executor = Executors.newCachedThreadPool();
        Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        Executor executor = Executors.newSingleThreadExecutor();

        executor.execute(getTask());    
        executor.execute(getTask());
        executor.execute(getTask());
    }

    private static Runnable getTask() {
        return () -> System.out.println("Hello from " + Thread.currentThread());
    }


}
