package slogo.model;

import java.util.List;

public interface Parser {

    /**
     * This method encompasses the bulk of the parser's job as it tries to break up user input into pieces that it can recognize as commands
     * The parser will most likely be implemented as some sort of class, which will store the parsed commands
     * in some sort of data structure.
     * This is part of the external back-end API so the front-end can tell the back-end when to start parsing
     * @param consoleInput this is the input by the user
     * @throws invalidCommandException if some command that is being parsed is invalid
     */
    public void parseCode(String consoleInput) throws invalidCommandException;

    /**
     * This method can be used by the internal back-end API when it needs to retrieve a certain command based on a string
     * @param commandInput the name of the command
     * @return a Command associated with the input
     * @throws invalidCommandException if the command name is invalid
     */
    public Command getCommand(String commandInput) throws invalidCommandException;

}
