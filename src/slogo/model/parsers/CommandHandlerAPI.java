package slogo.model.parsers;

import slogo.model.interfaces.CommandHandler;

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
