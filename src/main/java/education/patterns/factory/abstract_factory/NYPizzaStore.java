package education.patterns.factory.abstract_factory;

public class NYPizzaStore extends PizzaStoreAbstract {
    @Override
    protected PizzaAbstract createPizza(String type) {
        PizzaAbstract pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
        if (type.equals("cheese")) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("NY style cheese pizza");
        }
        return pizza;
    }
}
