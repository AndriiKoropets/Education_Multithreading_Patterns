package education.patterns.strategy.game;

public class King extends Character{

    public King(WeaponBehaviour weaponBehaviour) {
        super(weaponBehaviour);
    }
    @Override
    public void display() {
        System.out.println("I am a king");
    }
}
