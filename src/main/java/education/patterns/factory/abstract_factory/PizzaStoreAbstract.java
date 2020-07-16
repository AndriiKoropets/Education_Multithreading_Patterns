package education.patterns.factory.abstract_factory;

public abstract class PizzaStoreAbstract {

    public PizzaAbstract orderPizza(String type) {
        PizzaAbstract pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    protected abstract PizzaAbstract createPizza(String type);
}
