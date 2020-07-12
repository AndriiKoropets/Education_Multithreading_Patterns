package education.patterns.strategy.duck;

public class Squeak implements QuackBehaviour{
    @Override
    public void performQuack() {
        System.out.println("Squeak");
    }
}
