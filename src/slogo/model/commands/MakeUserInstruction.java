package slogo.model.commands;

import java.util.ArrayList;
import java.util.List;

public class MakeUserInstruction implements Command<Double> {
//    test [" :x :y"] [ " fd x rt y"]
//    UserVariable newVar = new UserVariable();
//        newVar.setValue(0.0);
//
//    test 50 60
//
//    fd 50 rt 60

    private List<String> commandList;
    private Integer userVal;
    private Integer commandCounter;
    private Integer unexecutedCounter;
    private StringBuilder str=new StringBuilder();;
    private String LEFT_BRACKET = "[";
    private String RIGHT_BRACKET = "]";
    private int leftBracketCounter=0;
    private int rightBracketCounter=0;
    private List<String> leftCommand = new ArrayList<>();
    private List<String> rightCommand = new ArrayList<>();
    private String commandName;

    public MakeUserInstruction(List<String> sCom, String name, Integer counter) {
        this.commandList = sCom;
        this.commandName = name;
        this.commandCounter = counter;
        System.out.println("counter: " + commandCounter);
    }

    @Override
    public Double execute() {

        return 0.0;
    }
    @Override
    public boolean isItExecutable() {
        return true;
    }
}
