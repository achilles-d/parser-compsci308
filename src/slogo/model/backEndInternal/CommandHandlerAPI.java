package slogo.model.backEndInternal;

import slogo.model.CommandHandler;
import slogo.model.backEndInternal.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandHandlerAPI implements CommandHandler {

    private List<String> commands;
    public CommandHandlerAPI(){
        commands=new ArrayList<>();
    }

    @Override
    public List<String> getCommandHistory() {
        return commands;
    }

    @Override
    public void updateCommandHistory(String nextCommand) {
        commands.add(nextCommand);
    }

}
