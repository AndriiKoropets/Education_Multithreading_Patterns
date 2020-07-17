package education.patterns.template;

public class TeaWithHook extends CaffeineBeverageWithHook{
    @Override
    protected void brew() {
        System.out.println("Dripping tea through filter");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding lemon");
    }
}
