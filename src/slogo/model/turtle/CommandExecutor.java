package slogo.model.turtle;

import slogo.model.commands.Command;
import slogo.model.interfaces.Executor;

public class CommandExecutor implements Executor {
    
    @Override

    public Object executeCommand(Command currentCommand) throws Exception {
        return currentCommand.execute();
    }


}
