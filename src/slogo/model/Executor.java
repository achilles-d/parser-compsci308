package slogo.model;

import slogo.model.backEndInternal.commands.Command;

public interface Executor {


    /**
     * This is part of the internal back-end API, and will be used to actually execute and run specific commands
     * @param currentCommand is the command that is trying to be run
     * @throws ExecutionException when there is some error that occurs when trying to run the command.
     * executionError could be extended to create more specific types of errors. For example, there could be math errors like divide by 0
     */
    public void executeCommand(Command currentCommand) throws ExecutionException;

}

