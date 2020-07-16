package education.patterns.command.ceiling_fan;

import education.patterns.command.Command;

public class NoCommand implements Command {
    @Override
    public void execute() {
        //do nothing
    }

    @Override
    public void undo() {
        //do nothing
    }
}
