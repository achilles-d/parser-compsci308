package slogo.model.backEndInternal.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import slogo.model.backEndInternal.UserVariable;
import slogo.model.backEndInternal.UserVariableHandler;

public class Repeat implements Command<Object> {

  private  Stack<String> stack;
  private Command num;
  private Command group;
  private List<String> groupedCodes;
  private int repeatSize;

  private List<String> commandsToAddToStack;

  public Repeat(Stack<String> stack, Command num, Command group) {
   this.num=num;
   this.group=group;
   this.stack=stack;

  }

  @Override
  public Object execute() {

    groupedCodes= (List<String>) group.execute();
    repeatSize= (int) num.execute();
    cleanTheFirstLayerBrackets();
    repeatCommands();
    if(commandsToAddToStack.size()==0){
      stack.addAll(commandsToAddToStack);
    } else{
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
   * @return the new counter
   */
  public Integer updateCounter(){
    return -1;
  }

  /**
   *
   * @return the new commandList which is not yet executed
   */
  public List<String> updateRawCommands(){
    //return Arrays.asList(("fd 50").split(" "));
    return null;
  }

}
