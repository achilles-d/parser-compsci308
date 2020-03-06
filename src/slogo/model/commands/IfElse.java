package slogo.model.commands;

import java.util.List;

public class IfElse implements Command<Object> {

    private List<String> commandListLeft;
    private List<String> commandListRight;
    private String LEFT_BRACKET = "[";
    private String RIGHT_BRACKET = "]";
    private Double userVal;
    private boolean isExecutable = true;

    public IfElse(Command con, Command commandLeft, Command commandRight) {
        this.userVal = (Double) con.execute();
        this.commandListLeft = (List<String>) commandLeft.execute();
        this.commandListRight = (List<String>) commandRight.execute();

        System.out.println(" COMMAND LEFT : " + commandLeft);

        System.out.println(" COMMAND RIGHT : " + commandRight);
        //isExecutable(userVal);
    }


    @Override
    public Object execute() {
        if (userVal>0) {
            commandListLeft.remove(LEFT_BRACKET);
            commandListLeft.remove(commandListLeft.lastIndexOf(RIGHT_BRACKET));
            return commandListLeft;
        } else {
            commandListRight.remove(LEFT_BRACKET);
            commandListRight.remove(commandListRight.lastIndexOf(RIGHT_BRACKET));
            return commandListRight;
        }
    }
//
//    private void isExecutable(Double userVal) {
//        if (userVal != 0 && commandListRight.size() != 0) {
//            isExecutable = false;
//        }
//    }

    @Override
    public boolean isItExecutable() {
        return false;
    }

}
