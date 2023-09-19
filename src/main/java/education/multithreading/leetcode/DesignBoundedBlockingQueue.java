package education.multithreading.leetcode;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DesignBoundedBlockingQueue<E> {

    private final int size;
    private final AtomicInteger currentSize;
    private final Lock lock = new ReentrantLock();
    private final Condition emptyQueue = lock.newCondition();
    private final Condition fullQueue = lock.newCondition();
    private LinkedList<E> queue;


    public DesignBoundedBlockingQueue(int size) {
        this.size = size;
        this.currentSize  = new AtomicInteger(0);
        queue = new LinkedList<>(null, null);
    }

    public E poll() {
        lock.lock();
        try {
            while (currentSize.get() == 0) {
                emptyQueue.await();
            }
            E value = queue.value;
            queue = queue.next;
            currentSize.decrementAndGet();
            fullQueue.signalAll();
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public E peek() {
        lock.lock();
        try {
            while (currentSize.get() == 0) {
                emptyQueue.await();
            }
            E value = queue.value;
            fullQueue.signalAll();
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public void add(E elem) {
        lock.lock();
        try {
            while (currentSize.get() == size) {
                System.out.println("Queue is full.");
                fullQueue.await();
            }
            if (currentSize.get() == 0) {
                queue = new LinkedList<>(elem, null);
            } else {
                LinkedList<E> lastNode = queue;
                for (int i = 0; i < currentSize.get() - 1; i++) {
                    lastNode = lastNode.next;
                }
                lastNode.next = new LinkedList<>(elem, null);
            }

            currentSize.getAndIncrement();
            emptyQueue.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void remove() {
        lock.lock();
        try {
            while (queue.value == null && queue.next == null) {
                emptyQueue.await();
            }
            queue = queue.next;
            currentSize.decrementAndGet();
            fullQueue.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return queue != null ? queue.toString() : "Queue is empty now";
    }

    public static void main(String[] args) throws InterruptedException {
        DesignBoundedBlockingQueue<Integer> myBlockingQueue = new DesignBoundedBlockingQueue<>(5);
        new Thread(() -> {
            myBlockingQueue.add(13);
            myBlockingQueue.add(2);
            myBlockingQueue.add(7);
            myBlockingQueue.add(1);
            myBlockingQueue.add(27);
            System.out.println("Position one = " + myBlockingQueue);
            myBlockingQueue.add(28);
            myBlockingQueue.add(30);
            System.out.println("Position two = " + myBlockingQueue);
        }).start();

        new Thread(() -> {
            System.out.println("Retrieved = " + myBlockingQueue.poll());
            System.out.println(myBlockingQueue);
        }).start();
        new Thread(() -> {
            myBlockingQueue.remove();
            System.out.println(myBlockingQueue);
        }).start();
        new Thread(() -> {
            System.out.println("Peeked = " + myBlockingQueue.peek());
            System.out.println(myBlockingQueue);
        }).start();
        Thread.sleep(1000);
        System.out.println(myBlockingQueue);


    }
}

class LinkedList<T> {
    T value;
    LinkedList<T> next;

    public LinkedList(T value, LinkedList<T> next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public String toString() {
        return next != null
                ? value.toString() + " -> " + next
                : value.toString();
    }
}
