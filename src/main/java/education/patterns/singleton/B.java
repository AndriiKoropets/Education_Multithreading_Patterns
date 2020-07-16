package education.patterns.singleton;

public class B extends A{
    private volatile static B instance;

    public static B getInstance() {
        if (instance == null) {
            synchronized (B.class) {
                instance = new B(12,"Artem");
            }
        }
        return instance;
    }

    private B(int age, String name) {
        super(age, name);
    }
}
