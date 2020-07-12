package education.patterns.strategy.game;

public class SwordBehaviour implements WeaponBehaviour{
    @Override
    public void useWeapon() {
        System.out.println("I am using sword to fight");
    }
}
