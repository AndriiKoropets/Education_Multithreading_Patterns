package education.multithreading.transaction_memory.app_2;

import org.multiverse.api.StmUtils;

import static org.multiverse.api.StmUtils.abort;

public class AccountTx {
    private final TnxInteger balance;

    public AccountTx(int balance) {
        this.balance = StmUtils.newTxnInteger(balance);
    }

    public void incBalance(final int amount) {
        balance.increment(amount);
        if (balance.get() < 0) {
            abort();
        }
    }

    public int getBalance() {
        return balance.get();
    }

    @Override
    public String toString() {
        return "Acc{" + balance.get() + '}';
    }
}
