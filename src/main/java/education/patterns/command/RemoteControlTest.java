package education.patterns.command;

public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();
        Light light = new Light();
        Command lightOn = new LightOnCommand(light);

        Garage garage = new Garage();
        Command garageDoorCommand = new GarageDoorCommand(garage);

        remote.setCommand(lightOn);
        remote.buttonOnWasPressed();
        remote.buttonOffWasPressed();

        remote.setCommand(garageDoorCommand);
        remote.buttonOnWasPressed();
        remote.buttonOffWasPressed();

    }
}
