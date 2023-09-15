package education.multithreading.transaction_memory.treiber_stack;


import org.multiverse.api.StmUtils;
import org.multiverse.api.collections.TxnStack;

import static org.multiverse.api.StmUtils.atomic;
import static org.multiverse.api.StmUtils.retry;

public class AppTx {
    public static void main(String[] args) {
        final TxnStack<String> stack0 = StmUtils.newTxnStack();
        final TxnStack<String> stack1 = StmUtils.newTxnStack();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignore) {/*NOP*/}

                    atomic(new Runnable() {
                        public void run() {
                            if (Math.random() > 0.5) {
                                stack0.push("A0");
                            } else {
                                stack1.push("A1");
                            }
                        }
                    });
                }
            }
        }).start();


        while (true) {
            atomic(new Runnable() {
                public void run() {
                    if (!stack0.isEmpty() && !stack1.isEmpty()) {
                        System.out.println(stack0.pop() + ":" + stack1.pop());
                    } else {
                        retry();
                    }
                }
            });
        }
    }
}
