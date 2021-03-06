package education.multithreading.jmm;

public class PetersonLock {
    private volatile boolean flag0 = false;
    private volatile boolean flag1 = false;
    private volatile int victim = 0;

    public void lockA() {
        flag0 = true; //I'm interested
        victim = 1;// you go first
        while (flag1 && victim == 1) {/*busy wait*/}
    }

    public void lockB() {
        flag1 = true;
        victim = 0;
        while (flag0 && victim == 0) {/*busy wait*/}
    }

    public void unlockA() {
        flag0 = false;//i'm not interested
    }

    public void unlockB() {
        flag1 = false;//i'm not interested
    }

}
