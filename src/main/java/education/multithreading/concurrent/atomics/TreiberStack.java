package education.multithreading.concurrent.atomics;

import java.util.concurrent.atomic.AtomicReference;

public class TreiberStack<T> {
    private AtomicReference<Node<T>> tail = null;
    public void push(T newElem) {
        Node<T> newTail = new Node<>(newElem, null);
        while (true) {
            Node<T> oldTail = this.tail.get();
            newTail.next = oldTail;
            if (tail.compareAndSet(oldTail, newTail)) {
                break;
            }
        }
    }
    public T pop() {
        while (true) {
            Node<T> oldTail = tail.get();
            Node<T> newTail = oldTail.next;
            if (tail.compareAndSet(oldTail, newTail)) {
                return oldTail.value;
            }
        }
    }


    private static class Node<E> {
        final E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }

    }
}
