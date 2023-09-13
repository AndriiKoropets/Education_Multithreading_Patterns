package education.multithreading.fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicLong;

public class App02_recursiveTask {

    public static void main(String[] args) {
        AtomicLong result = new AtomicLong(0);
        new ForkJoinPool().invoke(new App01_recursiveAction.Task(0, 1000_000, result));
        System.out.println(result.get());
    }

    public static class Task extends RecursiveTask<Long> {
        private final int from;
        private final int to;

        public Task(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            if (to - from < 10_000) {
                return directCalc();
            } else {
                int mid = (from + to) >>> 1;
                Task taskLeft = new Task(from, mid);
                Task taskRight = new Task(mid, to);
                taskLeft.fork();
                taskRight.fork();
                return taskLeft.join() + taskRight.join();
            }
        }

        private long directCalc() {
            long result = 0;
            for (int index = from; index < to; index++) {
                if (index % 3 != 0 && index % 5 != 0) {
                    result += index;
                }
            }
            return result;
        }
    }
}
