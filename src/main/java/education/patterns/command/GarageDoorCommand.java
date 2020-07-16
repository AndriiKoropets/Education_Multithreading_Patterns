package education.patterns.command;

public class GarageDoorCommand implements Command {
    private Garage garage;

    public GarageDoorCommand(Garage garage) {
        this.garage = garage;
    }

    @Override
    public void execute() {
        garage.up();
    }

    @Override
    public void undo() {
        garage.down();
    }
}
