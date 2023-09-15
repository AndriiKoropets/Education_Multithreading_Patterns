package education.multithreading.concurrent.task_executors;

import java.util.concurrent.Executor;

public class Demo01 {

    public static void main(String[] args) {
        Executor executor0 = getExecutor();
        executor0.execute(getTask());
        executor0.execute(getTask());

        Executor executor1 = getExecutor();
        executor1.execute(getTask());
        executor1.execute(getTask());
        executor1.execute(getTask());
    }

    private static Executor getExecutor() {
        throw new UnsupportedOperationException();
    }

    private static Runnable getTask() {
        throw new UnsupportedOperationException();
    }
}
