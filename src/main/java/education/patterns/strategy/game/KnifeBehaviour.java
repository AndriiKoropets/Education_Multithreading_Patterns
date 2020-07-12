package education.patterns.strategy.game;

public class KnifeBehaviour implements WeaponBehaviour{
    @Override
    public void useWeapon() {
        System.out.println("I am fighting with weapon");
    }
}
