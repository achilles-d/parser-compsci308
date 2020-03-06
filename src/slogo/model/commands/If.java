package slogo.model.commands;

import java.util.List;

public class If implements Command<Object>{

private List<String> commandList;
private Double userVal;

private String LEFT_BRACKET = "[";
private String RIGHT_BRACKET = "]";

private boolean isExecutable = true;

    public If(Command con, Command command){
        this.userVal = (Double) con.execute();
        this.commandList = (List<String>) command.execute();
        isExecutable(userVal);
    }

    @Override
    public Object execute() {

        if (userVal!=0) {
            commandList.remove(LEFT_BRACKET);
            commandList.remove(commandList.lastIndexOf(RIGHT_BRACKET));
            return commandList;
        }
        return 0.0;
    }

    private void isExecutable(Double userVal) {
        if (userVal != 0 && commandList.size() != 0) {
            isExecutable = false;
        }
    }

    @Override
    public boolean isItExecutable() {
        return isExecutable;
    }

}
