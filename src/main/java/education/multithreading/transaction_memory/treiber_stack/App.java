package education.multithreading.transaction_memory.treiber_stack;

import static org.multiverse.api.StmUtils.atomic;
import static org.multiverse.api.StmUtils.retry;

public class App {
    public static void main(String[] args) {
        final MyTxStack<String> stack0 = new MyTxStack();
        final MyTxStack<String> stack1 = new MyTxStack();

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
            atomic( new Runnable() {
                public void run() {
                    if (!stack0.isEmpty()) {
                        System.out.println(stack0.pop());
                    } else if (!stack1.isEmpty()) {
                        System.out.println(stack1.pop());
                    } else {
                        retry();
                    }
                }
            });
        }
    }
}
