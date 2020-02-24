package slogo.model.backEndInternal;

import slogo.model.CommandHandler;
import slogo.model.backEndInternal.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandHandlerAPI implements CommandHandler {

    private List<Command> commands;
    public CommandHandlerAPI(){
        commands=new ArrayList<>();
    }

    @Override
    public List<Command> getCommandHistory() {

        return commands;
    }

    @Override
    public void updateCommandHistory(Command nextCommand) {
        commands.add(nextCommand);

    }

}
