package education.patterns.factory.simple_factory;

public class NYStyleVeggiePizza extends Pizza{
    public NYStyleVeggiePizza() {
        super("NY vegan style sauce and cheese pizza",
                "Thin crust dough",
                "Marinara sauce");
        getToppings().add("Created Reggiano Cheese");
    }
}
