package education.patterns.strategy.game;

public class BowAndArrowBehaviour implements WeaponBehaviour{
    @Override
    public void useWeapon() {
        System.out.println("I am using bow and arrow to fight");
    }
}
