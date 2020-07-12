package education.patterns.strategy.duck;

public class Quack implements QuackBehaviour{
    @Override
    public void performQuack() {
        System.out.println("Quack");
    }
}
