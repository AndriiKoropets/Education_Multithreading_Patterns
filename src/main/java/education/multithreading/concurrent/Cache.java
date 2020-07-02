package education.multithreading.concurrent;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache {

    public <K, V> Map<K, V> synchronizedMap(Map<K, V> map) {
//        return new MyMap<>();
        return null;
    }

    private static class MyMap<T0, T1> implements Map<T0, T1> {
        private ReadWriteLock rwlock = new ReentrantReadWriteLock();
        private Lock rLock = rwlock.readLock();
        private Lock wLock = rwlock.writeLock();
        private final Map<T0, T1> m;

        private MyMap(Map<T0, T1> m) {
            this.m = m;
        }

        @Override
        public int size() {
            rLock.lock();
            try {
                return m.size();
            } finally {
                rLock.unlock();
            }
        }

        @Override
        public T1 put(T0 key, T1 value) {
            wLock.lock();
            try {
                return m.put(key, value);
            } finally {
                wLock.unlock();
            }

        }

        @Override
        public boolean isEmpty() {
            rLock.lock();
            try {
                return m.isEmpty();
            } finally {
                rLock.unlock();
            }
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public T1 get(Object key) {
            return null;
        }



        @Override
        public T1 remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<? extends T0, ? extends T1> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<T0> keySet() {
            return null;
        }

        @Override
        public Collection<T1> values() {
            return null;
        }

        @Override
        public Set<Entry<T0, T1>> entrySet() {
            return null;
        }
    }
}


class TTT {
    public static void main(String[] args) {
        ReadWriteLock rwLock = new ReentrantReadWriteLock();
        Lock r = rwLock.readLock();
        Lock w= rwLock.writeLock();

        new Thread(() -> {
            w.lock();
            System.out.println("2");
            while (true);
        }).start();

        new Thread(() -> {
            r.lock();
            System.out.println("0");
            while (true);
        }).start();

        new Thread(() -> {
            r.lock();
            System.out.println("1");
            while (true);
        }).start();


    }
}