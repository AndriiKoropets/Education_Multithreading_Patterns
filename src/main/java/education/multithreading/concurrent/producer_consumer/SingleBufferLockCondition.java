package education.multithreading.concurrent.producer_consumer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//1.fairness: w/b set - false or w/b queue - true
//2.lock/unlock - independent
public class SingleBufferLockCondition {
    private final Lock lock = new ReentrantLock(true);
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    private Integer elem = null;

    public void put(int newElem) throws InterruptedException {
        lock.lock();
        try {
            while (this.elem != null) {
                notFull.await();//await to null
            }
            this.elem = newElem;
            notEmpty.signal();//signal not signal all
        } finally {
            lock.unlock();
        }
    }

    public boolean tryLock(int newElem) throws InterruptedException {
        if (lock.tryLock()) {
            try {
                while (this.elem != null) {
                    notFull.await();
                }
                this.elem = newElem;
                notEmpty.signal();
                return true;
            } finally {
                lock.unlock();
            }
        } else {
            return false;
        }
    }

    public boolean tryLock(int newElem, long timeout, TimeUnit unit) throws InterruptedException {
        if (lock.tryLock(timeout, unit)) {
            try {
                while (this.elem != null) {
                    notFull.await();
                }
                this.elem = newElem;
                notEmpty.signal();
                return true;
            } finally {
                lock.unlock();
            }
        } else {
            return false;
        }
    }

    public int get() throws InterruptedException {
        lock.lock();
        try {
          while (elem == null) {
              notEmpty.await();
          }
          Integer result = this.elem;
          this.elem = null;
          notFull.signal();
          return result;
        } finally {
            lock.unlock();
        }
    }
}
