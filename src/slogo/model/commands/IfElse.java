package slogo.model.commands;

import java.util.List;

public class IfElse implements Command<Object> {

    private List<String> commandListLeft;
    private List<String> commandListRight;
    private Double userVal;
    private Command con;
    private Command commandRight;
    private Command commandLeft;

    /**
     * Command constructor
     * @param con constant to check
     * @param commandLeft set of commands to do
     * @param commandRight other set of commands to do
     */
    public IfElse(Command con, Command commandLeft, Command commandRight) {
        this.con=con;
        this.commandLeft=commandLeft;
        this.commandRight=commandRight;
    }


    /**
     * Execution logic
     * @return Object of command list to execute
     */
    @Override
    public Object execute() {
        this.userVal = (Double) con.execute();
        this.commandListLeft = (List<String>) commandLeft.execute();
        this.commandListRight = (List<String>) commandRight.execute();
        if (userVal>0) {
            commandListLeft.remove(0);
            commandListLeft.remove(commandListLeft.size()-1);
            return commandListLeft;
        } else {
            commandListRight.remove(0);
            commandListRight.remove(commandListRight.size()-1);
            return commandListRight;
        }
    }

    /**
     * Check if executable
     * @return Is it an executable command or not.
     */
    @Override
    public boolean isItExecutable() {
        return false;
    }

}
