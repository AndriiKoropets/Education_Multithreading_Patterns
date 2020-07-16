package education.patterns.factory.abstract_factory;

public class Main {
    public static void main(String[] args) {
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

        PizzaStoreAbstract pizzaStoreAbstract = new NYPizzaStore();
        pizzaStoreAbstract.orderPizza("cheese");
        PizzaAbstract pizza = pizzaStoreAbstract.createPizza("cheese");
        System.out.println(pizza);
        pizza = new CheesePizza(ingredientFactory);


        System.out.println(pizza);



    }
}
