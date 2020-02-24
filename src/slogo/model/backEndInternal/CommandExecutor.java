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
       //System.out.println("So far good"+currentCommand.execute().toString());

       return (Double) currentCommand.execute();
    }


}
