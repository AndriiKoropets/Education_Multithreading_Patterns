package education.multithreading.transaction_memory.app_1;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static Random rnd = new Random();
    static ExecutorService exec = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        System.out.println(Runtime.getRuntime().availableProcessors());

        final AccountLock[] accounts = new AccountLock[] {
                new AccountLock(100), new AccountLock(0),
                new AccountLock(100), new AccountLock(0),
                new AccountLock(100), new AccountLock(0),
                new AccountLock(100), new AccountLock(0),
                new AccountLock(100), new AccountLock(0)
        };

        Arrays.sort(accounts, (o1, o2) ->  - o1 .id + o2.id);

        for (int k = 0; k < 4 * Runtime.getRuntime().availableProcessors(); k++) {
            exec.submit((Runnable) () -> {
                while (true) {
                    int from = rnd.nextInt(accounts.length);
                    int to = rnd.nextInt(accounts.length);
                    if (from != to) {
                        int delta = rnd.nextInt(50);
                        transfer(accounts[from], accounts[to], delta);
                    }
                }
            });
        }

        Thread.sleep(1000);
        System.out.println(sum(accounts));
        System.out.println(toStr(accounts));
    }

    public static void transfer(final AccountLock from, final AccountLock to, final int amount) {
        AccountLock fst = (from.id < to.id) ? from : to;
        AccountLock snd = (from.id >= to.id) ? from : to;
        fst.lock.lock();
        try {
            snd.lock.lock();
            try {
                if (from.incBalance(-amount)) {
                    if (!to.incBalance(+amount)) {
                        from.incBalance(+amount); //rollback
                    }
                }
            } finally {
                snd.lock.unlock();
            }
        } finally {
            fst.lock.unlock();
        }
    }

    public static int sum(final AccountLock[] accounts) throws Exception {
        final AccountLock[] tmp = accounts.clone();
//        Arrays.sort(tmp, (o1, o2) -> o1 .id - o2.id);

        return lockRecursively(tmp, () -> {
            int result = 0;
            for (AccountLock acc : tmp) {
                result += acc.getBalance();
            }
            return result;
        });
    }

    public static String toStr(final AccountLock[] accounts) throws Exception {
        final AccountLock[] tmp = accounts.clone();
        Arrays.sort(tmp, (o1, o2) -> o1.id - o2.id);

        return lockRecursively(tmp, () -> Arrays.toString(tmp));
    }

    private static <T> T lockRecursively(AccountLock[] accounts, Callable<T> c) throws Exception {
        if (accounts.length > 0) {
            accounts[0].lock.lock();
            try {
                return lockRecursively(Arrays.copyOfRange(accounts, 1, accounts.length), c);
            } finally {
                accounts[0].lock.unlock();
            }
        } else {
            return c.call();
        }
    }
}
