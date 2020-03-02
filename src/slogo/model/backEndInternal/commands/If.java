package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.CommandHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class If extends CommandHelper implements Command<Double>{

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


    public If(Double userVal, List<String> commandList, Integer commandCounter){
        this.userVal=userVal.intValue();
        this.commandList=commandList;
        this.commandCounter=commandCounter;
        unexecutedCounter=commandList.size();

    }

    @Override
    public Double execute() {

        if(userVal!=0){

            findCommandToExecute();

            System.out.println(" Counter "+ commandCounter);

            System.out.println(" Counter "+ commandCounter);
            System.out.println("String builder "+ str.toString());

            rightCommand=commandList.subList(commandCounter, unexecutedCounter);

            leftCommand.addAll(Arrays.asList(str.toString().split(" ")));
            commandList=leftCommand;
            commandList.addAll(rightCommand);

            System.out.println(" commandlist command " + commandList.toString());
            //leftCommand.addAll(commandList);

            System.out.println(" left command " + leftCommand.toString());
            return Double.MAX_VALUE;

        }
        return Double.valueOf(0);

    }

    private void findCommandToExecute(){

        commandCounter+=2;// skip the square bracket
        leftBracketCounter+=1;

        while(commandCounter<unexecutedCounter){

            if(commandList.get(commandCounter).equals(LEFT_BRACKET)){
                leftBracketCounter++;
            } else if(commandList.get(commandCounter).equals(RIGHT_BRACKET)){
                rightBracketCounter++;
            }

            if(leftBracketCounter==rightBracketCounter){
                commandCounter++;
                break;
            } else{
                str.append(commandList.get(commandCounter)).append(" ");
            }

            System.out.println("String builder "+ commandList.get(commandCounter));

            commandCounter++;

        }
    }



    @Override
    public List<String> updateRawCommands() {
        return commandList;
    }

    @Override
    public Integer updateCounter() {
        return null;
    }

}
