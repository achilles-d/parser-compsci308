package slogo.model.commands;

import java.util.List;

public class If implements Command<Object>{

private List<String> commandList;
private Double userVal;

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
        if (userVal!=0) {
            commandList.remove(0);
            commandList.remove(commandList.size()-1);
            return commandList;
        }
        return 0.0;
    }

    @Override
    public boolean isItExecutable() {
        return isExecutable;
    }

}
