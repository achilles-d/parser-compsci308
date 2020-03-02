package slogo.model.backEndInternal.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Repeat implements Command<Object> {

  private List<String> groupedCodes;
  private int repeatSize;
  private boolean isItExecutable;

  private List<String> commandsToAddToStack;

  public Repeat(Command num, Command group) {

    this.groupedCodes= (List<String>) group.execute();
    this.repeatSize=  ((Double) num.execute()).intValue();
    commandsToAddToStack=new ArrayList<>();
    cleanTheFirstLayerBrackets();
    repeatCommands();

    isItExecutable=commandsToAddToStack.size()==0;
  }

  @Override
  public Object execute() {
    if(isItExecutable){
      return 0.0;
    }
    return commandsToAddToStack;
  }

  private void cleanTheFirstLayerBrackets() {
    groupedCodes.remove(0);
    groupedCodes.remove(groupedCodes.size()-1);
  }

  private void repeatCommands() {

    String LEFT_BRACKET = "[";
    int leftBracketIndex=groupedCodes.indexOf(LEFT_BRACKET);
    String RIGHT_BRACKET = "]";
    int righBracketIndex=groupedCodes.lastIndexOf(RIGHT_BRACKET);

    for (int i = 0; i < repeatSize; i ++) {
      for(String str:groupedCodes){

        if(leftBracketIndex<=i && i<= righBracketIndex){
          commandsToAddToStack.add(str);
        continue;
      }

        if(str.equals(":repcount")){
          commandsToAddToStack.add(Integer.toString(i));
        } else{
          commandsToAddToStack.add(str);
        }

      }
    }
  }


  /**
   *
   * @return the new commandList which is not yet executed
   */
  public boolean isItExecutable(){
    return isItExecutable;
  }

}
