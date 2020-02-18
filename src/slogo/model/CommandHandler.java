package slogo.model;

import java.util.List;

public interface CommandHandler {

    public List<Command> getCommandHistory();
    public void updateCommandHistory(Command nextCommand);

}
