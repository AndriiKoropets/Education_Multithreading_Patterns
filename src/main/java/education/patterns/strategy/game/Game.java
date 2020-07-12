package education.patterns.strategy.game;

public class Game {
    public static void main(String[] args) {
        Character character = new King(new SwordBehaviour());
        character.display();
        character.fight();
        character.setWeapon(new BowAndArrowBehaviour());
        character.fight();


        Character character1 = new Troll(new AxeBehaviour());
        character1.display();
        character1.fight();
        character1.setWeapon(new SwordBehaviour());
        character1.fight();
    }
}
