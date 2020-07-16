package education.patterns.factory.abstract_factory;

public class CheesePizza extends PizzaAbstract{
    private PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("Preparing = " + getName());
        setDough(ingredientFactory.createDough());
        setCheese(ingredientFactory.createCheese());
        setSauce(ingredientFactory.createSauce());
        setClams(ingredientFactory.createClams());
    }
}
