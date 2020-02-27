package slogo.model.backEndInternal.commands;

import java.util.List;

public class For implements Command<Double> {

    private int commandCounter;
    private int unexecutedCounter;
    private List<String> commandList;
    private String LEFT_BRACKET = "[";
    private String RIGHT_BRACKET = "]";
    private int start;
    private int end;
    private int increment;
    private String vairableName;
    private int numberOfLeft=0;
    private int numberOfRight=0;
    private int forLoopRun;
    public For(List<String> commandList, Integer counter){
        this.commandCounter=counter;
        this.commandList=commandList;
        this.unexecutedCounter=commandList.size();

    }


    @Override
    public Double execute() {

        if(commandList.get(commandCounter+1).equals(LEFT_BRACKET)){
            commandCounter+=2;
            vairableName=commandList.get(commandCounter);
            start=Integer.parseInt(commandList.get(commandCounter+1));
            end=Integer.parseInt(commandList.get(commandCounter+2));
            increment=Integer.parseInt((commandList.get(commandCounter+3)));
            commandCounter+=4; // ends skip the closing bracket
        }



        return null;
    }

    private void modifyTheCommand(){
        if(commandList.get(commandCounter).equals(LEFT_BRACKET)){
            commandCounter+=1;
            numberOfLeft+=1;
            forLoopRun=((end-start)/increment);

            for (int i=start; i<=end; i+=increment)

            while(numberOfLeft>numberOfRight){
                
            }
        }


    }

    @Override
    public List<String> updateRawCommands() {
        return null;
    }

    @Override
    public Integer updateCounter() {
        return null;
    }
}
