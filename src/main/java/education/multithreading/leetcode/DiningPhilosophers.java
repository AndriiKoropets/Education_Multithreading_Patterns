package education.multithreading.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import static java.util.Arrays.asList;

public class DiningPhilosophers {

    Semaphore latch_1 = new Semaphore(0);
    Semaphore latch_2 = new Semaphore(2);
    Semaphore latch_3 = new Semaphore(0);
    Semaphore latch_4 = new Semaphore(1);
    Semaphore latch_5 = new Semaphore(2);
    Semaphore leftForkSemaphore = new Semaphore(2);
    Semaphore rightForkSemaphore = new Semaphore(3);

    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        if (philosopher == 0) {
            latch_1.acquire(2);
        }
        if (philosopher == 1) {
            latch_2.acquire(2);
        }
        if (philosopher == 2) {
            latch_3.acquire(2);
        }
        if (philosopher == 3) {
            latch_4.acquire(1);
        }
        if (philosopher == 4) {
            latch_5.acquire(2);
        }
        rightForkSemaphore.acquire();
        pickRightFork.run();
        leftForkSemaphore.acquire();
        pickLeftFork.run();
        eat.run();
        putLeftFork.run();
        leftForkSemaphore.release();
        putRightFork.run();
        rightForkSemaphore.release();
        if (philosopher == 4 || philosopher == 1) {
            latch_1.release();
        }
        if (philosopher == 1 || philosopher == 3) {
            latch_3.release();
        }
        if (philosopher == 3 || philosopher == 0) {
            latch_5.release();
        }
        if (philosopher == 0 || philosopher == 2) {
            latch_2.release();
        }
        if (philosopher == 2 || philosopher == 4) {
            latch_4.release();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        List<List<Integer>> output = new ArrayList<>();
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
        Philosopher one = new Philosopher(0);
        Philosopher two = new Philosopher(1);
        Philosopher three = new Philosopher(2);
        Philosopher four = new Philosopher(3);
        Philosopher five = new Philosopher(4);
        Fork oneF = new Fork();
        Fork twoF = new Fork();
        Fork threeF = new Fork();
        Fork fourF = new Fork();
        Fork fiveF = new Fork();

        Runnable pickLeft_one = () -> one.pickLeftFork(twoF);
        Runnable pickRight_one = () -> one.pickRightFork(oneF);
        Runnable eat_one = one::eat;
        Runnable putLeft_one = () -> one.putLeftFork(twoF);
        Runnable putRight_one = () -> one.putRightFork(oneF);

        Runnable pickLeft_two = () -> two.pickLeftFork(threeF);
        Runnable pickRight_two = () -> two.pickRightFork(twoF);
        Runnable eat_two = two::eat;
        Runnable putLeft_two = () -> two.putLeftFork(threeF);
        Runnable putRight_two = () -> two.putRightFork(twoF);

        Runnable pickLeft_three = () -> three.pickLeftFork(fourF);
        Runnable pickRight_three = () -> three.pickRightFork(threeF);
        Runnable eat_three = three::eat;
        Runnable putLeft_three = () -> three.putLeftFork(fourF);
        Runnable putRight_three = () -> three.putRightFork(threeF);

        Runnable pickLeft_four = () -> four.pickLeftFork(fiveF);
        Runnable pickRight_four = () -> four.pickRightFork(fourF);
        Runnable eat_four = four::eat;
        Runnable putLeft_four = () -> four.putLeftFork(fiveF);
        Runnable putRight_four = () -> four.putRightFork(fourF);

        Runnable pickLeft_five = () -> five.pickLeftFork(oneF);
        Runnable pickRight_five = () -> five.pickRightFork(fiveF);
        Runnable eat_five = five::eat;
        Runnable putLeft_five = () -> five.putLeftFork(oneF);
        Runnable putRight_five = () -> five.putRightFork(fiveF);

        Runnable taskOne = () -> {
            try {
                diningPhilosophers.wantsToEat(one.id, pickLeft_one, pickRight_one, eat_one, putLeft_one, putRight_one);
                output.addAll(one.output);
                one.output.clear();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable taskTwo = () -> {
            try {
                diningPhilosophers.wantsToEat(two.id, pickLeft_two, pickRight_two, eat_two, putLeft_two, putRight_two);
                output.addAll(two.output);
                two.output.clear();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable taskThree = () -> {
            try {
                diningPhilosophers.wantsToEat(three.id, pickLeft_three, pickRight_three, eat_three, putLeft_three, putRight_three);
                output.addAll(three.output);
                three.output.clear();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable taskFour = () -> {
            try {
                diningPhilosophers.wantsToEat(four.id, pickLeft_four, pickRight_four, eat_four, putLeft_four, putRight_four);
                output.addAll(four.output);
                four.output.clear();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable taskFive = () -> {
            try {
                diningPhilosophers.wantsToEat(five.id, pickLeft_five, pickRight_five, eat_five, putLeft_five, putRight_five);
                output.addAll(five.output);
                five.output.clear();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 5; i++) {

            executorService.submit(taskOne);
            executorService.submit(taskTwo);
            executorService.submit(taskThree);
            executorService.submit(taskFour);
            executorService.submit(taskFive);


            Thread.sleep(1000);
            System.out.println(output.size());
            System.out.println(output);
        }

        executorService.shutdown();
    }
}

class Philosopher {
    int id;
    boolean hasLeftFork;
    boolean hasRightFork;
    boolean eat;

    final List<List<Integer>> output = new ArrayList<>();

    public Philosopher(int id) {
        this.id = id;
    }

    public void pickLeftFork(Fork fork) {
        if (!fork.taken) {
            this.hasLeftFork = true;
            fork.taken = true;
            output.add(asList(id, 1, 1));
        }
    }

    public void pickRightFork(Fork fork) {
        if (!fork.taken) {
            this.hasRightFork = true;
            fork.taken = true;
            output.add(asList(id, 2, 1));
        }
    }

    public void eat() {
        if (hasLeftFork && hasRightFork) {
            this.eat = true;
            output.add(asList(id, 0, 3));
        }
    }

    public void putLeftFork(Fork fork) {
        this.eat = false;
        this.hasLeftFork = false;
        fork.taken = false;
        output.add(asList(id, 1, 2));
    }

    public void putRightFork(Fork fork) {
        this.eat = false;
        this.hasRightFork = false;
        fork.taken = false;
        output.add(asList(id, 2, 2));
    }
}

class Fork {
    volatile boolean taken;
}

