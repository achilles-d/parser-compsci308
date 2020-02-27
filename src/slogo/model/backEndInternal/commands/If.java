package slogo.model.backEndInternal.commands;

import java.util.List;

public class If implements Command<Double>{

private List<String> commandList;
private Integer userVal;

    public If(Double userVal, List<String> commandList){
        this.userVal=userVal.intValue();
        this.commandList=commandList;

    }

    @Override
    public Double execute() {

        if(userVal!=0){
            
        } else {
            return Double.valueOf(0);
        }

        return null;
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
