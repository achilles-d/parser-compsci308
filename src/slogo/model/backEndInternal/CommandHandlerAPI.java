package slogo.model.backEndInternal;

import slogo.model.CommandHandler;
import slogo.model.backEndInternal.commands.Command;

import java.util.List;

public class CommandHandlerAPI implements CommandHandler {
    @Override
    public List<Command> getCommandHistory() {

        return null;
    }

    @Override
    public void updateCommandHistory(Command nextCommand) {

    }

}
