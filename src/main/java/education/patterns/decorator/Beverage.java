package education.patterns.decorator;

public abstract class Beverage {
    private String description = "Unknown description";

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract double cost();
}
