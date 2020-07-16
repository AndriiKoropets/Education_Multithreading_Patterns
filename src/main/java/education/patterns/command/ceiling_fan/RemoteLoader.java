package education.patterns.command.ceiling_fan;

public class RemoteLoader {

    public static void main(String[] args) {
        RemoteControlWithUndo remoteControlWithUndo = new RemoteControlWithUndo();

        CeilingFan ceilingFan = new CeilingFan("Living room");
        CeilingHighFanCommand ceilingHighFanCommand = new CeilingHighFanCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOffCommand = new CeilingFanOffCommand(ceilingFan);

        remoteControlWithUndo.setCommand(0, ceilingHighFanCommand, ceilingFanOffCommand);

        remoteControlWithUndo.onButtonWasPressed(0);
        System.out.println(remoteControlWithUndo);
    }
}
