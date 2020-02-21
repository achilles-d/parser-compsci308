package slogo.model.backEndInternal;

import slogo.model.Command;
import slogo.model.ExecutionException;
import slogo.model.Executor;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor implements Executor {
    Map<String, Runnable> commands;
    public CommandExecutor(){

    }
    @Override
    /**
     *
     */
    public void executeCommand(Command currentCommand) throws ExecutionException {
       commands.get(currentCommand).run();
    }

    private void matchCommands(){
        commands = new HashMap<>();

        // Populate commands map
        commands.put("fs", () -> System.out.println("Help"));
        commands.put("t", () -> System.out.println("Teleport"));

        // Invoke some command
        String cmd = new String("t");
        commands.get(cmd).run();   // Prints "Teleport"
    }
}
