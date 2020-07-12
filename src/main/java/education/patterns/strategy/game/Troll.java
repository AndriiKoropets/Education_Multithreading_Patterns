package education.patterns.strategy.game;

public class Troll extends Character{

    public Troll(WeaponBehaviour weaponBehaviour) {
        super(weaponBehaviour);
    }

    @Override
    public void display() {
        System.out.println("I am Troll");
    }
}
