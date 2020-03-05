package slogo.model.backEndInternal;

import slogo.model.backEndInternal.commands.Command;
import slogo.model.exceptions.ExecutionException;
import slogo.model.Executor;

public class CommandExecutor implements Executor {


    public CommandExecutor(){

    }
    @Override
    /**
     *
     */
    public Object executeCommand(Command currentCommand) throws Exception {
        return currentCommand.execute();
    }


}
