package education.multithreading.for_interview;

public class MultithreadingExample {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        Runnable thread1 = () -> System.out.println("Hello from thread = " + Thread.currentThread().getName());
        Runnable thread2 = new Thread(() -> System.out.println("Hello from thread = " + Thread.currentThread().getName()));
        Runnable thread3 = new Thread(() -> System.out.println("Hello from thread = " + Thread.currentThread().getName()));
//        thread1.run();
        thread1.run();

//        thread2.run();
        thread2.run();

//        thread3.run();
        thread3.run();

        thread3.notify();
    }
}
