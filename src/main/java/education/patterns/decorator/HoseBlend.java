package education.patterns.decorator;

public class HoseBlend extends Beverage{

    public HoseBlend() {
        setDescription("Hose Blend Coffee");
    }

    @Override
    public double cost() {
        return .89;
    }
}
