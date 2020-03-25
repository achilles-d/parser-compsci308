package slogo.model.commands;

/**
 * Command will definitely be implemented as an interface that can be implemented from, because there are many commands
 * that need objects to be made from. Therefore it is simple if they all implement this interface so that you can create
 * a common object out of all the commands and when they need to be executed you call the common execute method.
 * @param <T> This is a generic return type. This is used to provide flexibility to the program.
 */
public interface Command<T> {

    /**
     * This is common across all methods
     * @return generic return is used so that commands have flexibility in what they can return. Most return doubles
     * but some return lists or objects that are used to repeat commands or define when they end.
     */
    T execute();

    /**
     * Determine whether or not a command's results (return) needs to be added to the stack to be used as arguments
     * for the next commands.
     * @return boolean check
     */
    boolean isItExecutable();
}
