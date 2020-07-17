package education.patterns.template;

public class BeverageTestDriver {
    public static void main(String[] args) {
        TeaWithHook teaWithHook = new TeaWithHook();
        CoffeeWithHook coffeeWithHook = new CoffeeWithHook();

        System.out.println("\n Making Tea...");
        teaWithHook.prepareRecipe();

        System.out.println("\n Making coffee");
        coffeeWithHook.prepareRecipe();
    }
}
