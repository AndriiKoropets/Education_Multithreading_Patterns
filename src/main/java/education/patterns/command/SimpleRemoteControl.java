package education.patterns.command;

public class SimpleRemoteControl {
    Command slot;

    public SimpleRemoteControl() {
    }

    public void setCommand(Command command) {
        this.slot = command;
    }

    public void buttonOnWasPressed() {
        this.slot.execute();
    }

    public void buttonOffWasPressed() {
        this.slot.undo();
    }
}
