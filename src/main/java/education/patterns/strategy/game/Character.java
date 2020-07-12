package education.patterns.strategy.game;

public abstract class Character {
    private WeaponBehaviour weaponBehaviour;

    public Character(WeaponBehaviour weaponBehaviour) {
        this.weaponBehaviour = weaponBehaviour;
    }

    public abstract void display();

    public void fight() {
        weaponBehaviour.useWeapon();
    }

    void setWeapon(WeaponBehaviour weaponBehaviour) {
        this.weaponBehaviour = weaponBehaviour;
    }
}
