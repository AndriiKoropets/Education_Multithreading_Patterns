package education.multithreading.transaction_memory.app_3;

public class LatchTx {
    private TxnBoolean open = StmUtils.newTxnBoolean(false);

    public boolean isOpen() {
        return atomic(new TxnBooleanCallable() {
            public boolean call(Txn txn) {
                return open.get();
            }
        });
    }

    public void doOpen() {
        atomic(new Runnable() {
            public void run() {
                open.set(true);
            }
        });
    }

    public void await() {
        atomic(new Runnable() {
            public void run() {
                if (!open.get()) {
                    retry();//NOT busy waiting, NOT spin lock, NOT "while (!volatileFlag)
                }
            }
        });
    }

}
