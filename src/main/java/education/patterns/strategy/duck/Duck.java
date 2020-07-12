package education.patterns.strategy.duck;

public abstract class Duck {
    FlyBehaviour flyBehaviour;
    QuackBehaviour quackBehaviour;

    public Duck() {
    }

    public abstract void display();

    public void performFly() {
        flyBehaviour.fly();
    }

    public void performQuack() {
        quackBehaviour.performQuack();
    }

    public void swim() {
        System.out.println("All ducks float, even decoys!");
    }
}
