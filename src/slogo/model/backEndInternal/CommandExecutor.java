package slogo.model.backEndInternal;

import slogo.model.backEndInternal.commands.Command;
import slogo.model.ExecutionException;
import slogo.model.Executor;

public class CommandExecutor implements Executor {


    public CommandExecutor(){

    }
    @Override
    /**
     *
     */
    public Object executeCommand(Command currentCommand) throws ExecutionException {
        return currentCommand.execute();
    }


}
