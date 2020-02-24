package slogo.model;

import slogo.model.backEndInternal.commands.Command;

import java.lang.reflect.InvocationTargetException;

public interface Parser {

    /**
     * This method encompasses the bulk of the parser's job as it tries to break up user input into pieces that it can recognize as commands
     * The parser will most likely be implemented as some sort of class, which will store the parsed commands
     * in some sort of data structure.
     * This is part of the external back-end API so the front-end can tell the back-end when to start parsing
     * @param consoleInput this is the input by the user
     * @throws InvalidCommandException if some command that is being parsed is invalid
     */
    public void parseCode(String consoleInput) throws InvalidCommandException, ExecutionException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    /**
     * This method can be used by the internal back-end API when it needs to retrieve a certain command based on a string
     * @param commandInput the name of the command
     * @return a Command associated with the input
     * @throws InvalidCommandException if the command name is invalid
     */
    public Command getCommand(String commandInput) throws InvalidCommandException;

}
