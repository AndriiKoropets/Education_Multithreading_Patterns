package education.patterns.strategy.game;

public class AxeBehaviour implements WeaponBehaviour{
    @Override
    public void useWeapon() {
        System.out.println("I am fighting with axe");
    }
}
