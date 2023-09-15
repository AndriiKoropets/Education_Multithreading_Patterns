package education.multithreading.for_interview;

public class Foo {

    private volatile boolean forSecond;
    private volatile boolean forThird;
    private final Object secondMonitor;
    private final Object thirdMonitor;
    public Foo() {
        secondMonitor = new Object();
        thirdMonitor = new Object();
        forSecond = true;
        forThird = true;
    }

    public void first() { System.out.print("first"); }
    public void second() { System.out.print("second"); }
    public void third() { System.out.print("third"); }

    public synchronized void first(Runnable printFirst) throws InterruptedException {
        System.out.println("1-" + Thread.currentThread().getName());

//        notifyAll();
//        // printFirst.run() outputs "first". Do not change or remove this line.
//            printFirst.run();
//            forSecond = false;
//            secondMonitor.notify();

    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        System.out.println("2-" + Thread.currentThread().getName());

//            notifyAll();
//            while(forSecond) {
//                wait();
//            }
//            // printSecond.run() outputs "second". Do not change or remove this line.
//            printSecond.run();
//            forThird = false;
//            secondMonitor.notify();


    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        System.out.println("3-" + Thread.currentThread().getName());
//            notifyAll();
//            while (forSecond && forThird) {
//                wait();
//            }
//            // printThird.run() outputs "third". Do not change or remove this line.
//            printThird.run();
    }

    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        Thread a = new Thread(foo::first);
        Thread b = new Thread(foo::second);
        Thread c = new Thread(foo::third);

        b.start();
        c.start();
        a.start();


        foo.second(b);
        foo.third(c);
        foo.first(a);

    }

}
