package slogo.model.backEndInternal;

import slogo.model.backEndInternal.commands.Command;
import slogo.model.ExecutionException;
import slogo.model.Executor;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor implements Executor {

    public CommandExecutor(){

    }
    @Override
    /**
     *
     */
    public void executeCommand(Command currentCommand) throws ExecutionException {

       currentCommand.execute();
    }


}
