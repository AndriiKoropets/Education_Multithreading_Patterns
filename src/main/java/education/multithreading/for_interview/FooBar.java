package education.multithreading.for_interview;

class FooBar {
    private int n;

    private volatile boolean fooFlag = true;
    private volatile boolean barFlag = true;

    private final Object monitor = new Object();

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
//            synchronized (monitor) {
//                // printFoo.run() outputs "foo". Do not change or remove this line.
//                printFoo.run();
//
//                barFlag = false;
//                monitor.notifyAll();
//                while (fooFlag) {
//                    monitor.wait();
//
//                }
//                barFlag = true;
//            }
            while (!fooFlag);
            synchronized (monitor) {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                fooFlag = false;
            }

        }
    }

    public synchronized void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
//            synchronized (monitor) {
//                while (barFlag) {
//                    monitor.wait();
//                }
//
//                // printBar.run() outputs "bar". Do not change or remove this line.
//                printBar.run();
//                fooFlag = false;
//                monitor.notifyAll();
//                fooFlag = false;
////                fooFlag = true;
//            }
            while (fooFlag);
            synchronized (monitor) {
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                fooFlag = true;
            }
        }
    }

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(50);
        Runnable runA = () -> System.out.print("foo");
        Runnable runB = () -> System.out.print("bar");
        Thread a = new Thread(() -> {
            try {
                fooBar.foo(runA);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread b = new Thread(() -> {
            try {
                fooBar.bar(runB);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        a.start();
        b.start();

    }
}
