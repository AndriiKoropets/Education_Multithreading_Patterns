package education.multithreading.transaction_memory.app_2;

import org.multiverse.api.StmUtils;
import org.multiverse.api.references.TxnInteger;

import static org.multiverse.api.StmUtils.abort;

public class AccountTx {
    private final TxnInteger balance;

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
