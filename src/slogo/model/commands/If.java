package slogo.model.commands;

import java.util.List;

public class If implements Command<Object>{

private List<String> commandList;
private Double userVal;

private String LEFT_BRACKET = "[";
private String RIGHT_BRACKET = "]";
private Command con;
private Command command;

private boolean isExecutable ;

    public If(Command con, Command command){
        this.con=con;
        this.command=command;
        isExecutable=false;
    }

    @Override
    public Object execute() {
        this.userVal = (Double) con.execute();
        this.commandList = (List<String>) command.execute();
        //isExecutable(userVal);
        if (userVal!=0) {
            commandList.remove(LEFT_BRACKET);
            commandList.remove(commandList.lastIndexOf(RIGHT_BRACKET));
            return commandList;
        }
        return 0.0;
    }

    @Override
    public boolean isItExecutable() {
        return isExecutable;
    }

}
