package education.multithreading.jmm;

public class DekkerLock {
    private volatile boolean wontLockA = false;
    private volatile boolean wontLockB = false;
    private volatile int turn = 0;

    public void lockA() {
        wontLockA = true;
        while (wontLockB) {
            if (turn != 0) {
                wontLockA = false;
                while (turn != 0) {
                    /*busy waiting*/
                }
                wontLockA = true;
            }
        }
    }

    public void lockB() {
        wontLockB = true;
        while (wontLockA) {
            if (turn != 1) {
                wontLockB = false;
                while (turn != 1) {
                    /*busy waiting*/
                }
                wontLockB = true;
            }
        }
    }

    public void unlockA() {
        turn = 1;
        wontLockA = false;
    }

    public void unlockB() {
        turn = 0;
        wontLockB = false;
    }

    public static void main(String[] args) {
        final DekkerLock lock = new DekkerLock();
        new Thread(() -> {
            while (true) {
                lock.lockA();
                try {
                    System.out.println("A");
                } finally {
                    lock.unlockA();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                lock.lockB();
                try {
                    System.out.println("    B");
                } finally {
                    lock.unlockB();
                }
            }
        }).start();
    }

}
