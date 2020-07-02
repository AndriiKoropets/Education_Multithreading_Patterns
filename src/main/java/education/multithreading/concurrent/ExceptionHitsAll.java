package education.multithreading.concurrent;

public class ExceptionHitsAll {

    public static void main(String[] args) {
        final ThreadGroup group = new ThreadGroup("sub");

        Thread.UncaughtExceptionHandler exceptionHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                group.interrupt();
            }
        };

        Thread p0 = new Thread(group, () -> {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException ignore) {
                System.out.println("0 - killed");
            }
        });

        p0.setUncaughtExceptionHandler(exceptionHandler);
        p0.start();

        Thread p1 = new Thread(group, () ->{
            System.out.println("1 - dead");
            throw new Error();
        });
        p1.setUncaughtExceptionHandler(exceptionHandler);
        p1.start();
    }
}
