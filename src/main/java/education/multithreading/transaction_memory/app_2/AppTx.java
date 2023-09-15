package education.multithreading.transaction_memory.app_2;

import org.multiverse.api.Txn;
import org.multiverse.api.callables.TxnCallable;
import org.multiverse.api.callables.TxnIntCallable;
import org.multiverse.api.exceptions.DeadTxnException;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.multiverse.api.StmUtils.atomic;

public class AppTx {

    static Random rnd = new Random();
    static ExecutorService exec = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        System.out.println(Runtime.getRuntime().availableProcessors());

        final AccountTx[] accounts = new AccountTx[] {
                new AccountTx(100), new AccountTx(0),
                new AccountTx(100), new AccountTx(0),
                new AccountTx(100), new AccountTx(0),
                new AccountTx(100), new AccountTx(0),
                new AccountTx(100), new AccountTx(0)
        };

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

    public static boolean transfer(final AccountTx from, final AccountTx to, final int amount) {

        try {
            atomic(new Runnable() {
                @Override
                public void run() {
                    from.incBalance(-amount);
                    to.incBalance(+amount);
                }
            });
            return true;
        } catch (DeadTxnException e){
            return false;
        }
    }

    public static int sum(final AccountTx[] accounts) throws Exception {
        return atomic(new TxnIntCallable() {
            public int call(Txn txn) throws Exception {
                int result = 0;
                for (AccountTx account : accounts) {
                    result += account.getBalance();
                }
                return result;
            }
        });
    }

    public static String toStr(final AccountTx[] accounts) throws Exception {
        return atomic(new TxnCallable<String>() {
            public String call(Txn txn) throws Exception {
                return Arrays.toString(accounts);
            }
        });
        //Option.map
    }
}
