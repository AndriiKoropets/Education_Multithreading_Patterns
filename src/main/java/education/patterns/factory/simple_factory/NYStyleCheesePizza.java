package education.patterns.factory.simple_factory;

public class NYStyleCheesePizza extends Pizza{
    public NYStyleCheesePizza() {
        super("NY style sauce and cheese pizza",
                "Thin crust dough",
                "Marinara sauce");
        getToppings().add("Created Reggiano Cheese");
    }
}
