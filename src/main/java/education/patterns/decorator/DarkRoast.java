package education.patterns.decorator;

public class DarkRoast extends Beverage{

    public DarkRoast() {
        setDescription("Dark roast");
    }

    @Override
    public double cost() {
        return 1.2;
    }
}
