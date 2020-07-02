package education.multithreading.concurrent.task_executors;

public class Demo00 {
    public static void main(String[] args) {
        Runnable task = () -> System.out.println("Hello");
        new Thread(task).start();
    }
}
