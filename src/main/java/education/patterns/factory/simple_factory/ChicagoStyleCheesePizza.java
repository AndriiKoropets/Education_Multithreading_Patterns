package education.patterns.factory.simple_factory;

public class ChicagoStyleCheesePizza extends Pizza{
    public ChicagoStyleCheesePizza() {
        super("Chicago Style Deep Dish Cheese Pizza",
                "Extra Thick Crust Dough",
                "Plum Tomato Sauce");
        getToppings().add("Shredded Mozzarella Cheese");
    }

    @Override
    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
