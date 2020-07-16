package education.patterns.factory.abstract_factory;

public abstract class PizzaAbstract {
    private String name;
    private Dough dough;
    private Sauce sauce;
    private Cheese cheese;
    private Clams clams;

    public abstract void prepare();

    public void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }
    void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public String getName() {
        return name;
    }

    public Dough getDough() {
        return dough;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public Clams getClams() {
        return clams;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public void setCheese(Cheese cheese) {
        this.cheese = cheese;
    }

    public void setClams(Clams clams) {
        this.clams = clams;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PizzaAbstract{" +
                "name='" + name + '\'' +
                ", dough=" + dough +
                ", sauce=" + sauce +
                ", cheese=" + cheese +
                ", clams=" + clams +
                '}';
    }
}
