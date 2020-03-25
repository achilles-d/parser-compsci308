package slogo.model.commands;

public interface Command<T> {

    /**
     * This will be in the internal back-end API, and will essentially do what the command is supposed to do.
     * Command will definitely be implemented as a class that can be extended from, because there are commands that do similar things
     * like math commands, or movement commands. Each of them will implement execute differently based on what they need to do
     * @return
     */
    T execute();

    /**
     * This will be in the internal backend API and will tell us if the command is executable or a leaf node in the
     * command tree structure.
     * @return
     */
    boolean isItExecutable();
}
