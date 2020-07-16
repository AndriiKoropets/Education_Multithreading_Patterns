package education.patterns.singleton;

public class SingletonMultithreading {
    private volatile static SingletonMultithreading instance;
    public static SingletonMultithreading getInstance() {
        if (instance == null) {
            synchronized (SingletonMultithreading.class) {
                instance = new SingletonMultithreading();
            }
        }
        return instance;
    }

    private SingletonMultithreading() {}
}
