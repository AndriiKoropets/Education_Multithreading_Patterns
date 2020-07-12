package education.patterns.strategy.duck;

public class MuteQuack implements QuackBehaviour{
    @Override
    public void performQuack() {
        System.out.println("<< Silence >>");
    }
}
