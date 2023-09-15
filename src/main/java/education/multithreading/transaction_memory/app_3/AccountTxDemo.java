package education.multithreading.transaction_memory.app_3;

import education.multithreading.transaction_memory.app_2.AccountTx;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountTxDemo {
    public static void main(String[] args) {
        Object ref = new Object();
        synchronized (ref) {
            ref.notify();
        }

        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            lock.newCondition().signal();
        } finally {
            lock.unlock();
        }

        final AccountTx acc = new AccountTx(100);
//        StmUtils.atomic(new Runnable() {
//            public void run() {
                acc.incBalance(-20);
//            }
//        });

    }
}
