package education.multithreading.leetcode;

class FooBar {
    private int n;

    private volatile boolean flag = true;
    private final Object monitor = new Object();

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while(!flag);
            synchronized(monitor) {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
            }
            flag = false;
        }
    }

    public synchronized void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while(flag);
            synchronized(monitor) {
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
            }
            flag = true;
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
