package slogo.model.commands;

import java.util.List;

/**
 * Another example of a command (internally used) that takes advantage of generic returns. Lists of strings are returned
 * and can be handled by the parser.
 */
public class ListEnd implements Command<Object> {

    List<String> group;

    /**
     * Command constructor
     * @param group list of strings
     */
    public ListEnd(List<String> group){
        this.group=group;
    }

    /**
     * Execution logic
     * @return Double value argument
     */
    @Override
    public List<String> execute() {
        return group;
    }

    /**
     * Check if executable
     * @return Is it an executable command or not.
     */
    @Override
    public boolean isItExecutable() {
        return true;
    }
}
