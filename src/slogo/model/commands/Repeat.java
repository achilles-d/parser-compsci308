package slogo.model.commands;

import slogo.model.turtle.UserVariableHandler;

import java.util.ArrayList;
import java.util.List;


public class Repeat implements Command<Object> {

  private List<String> groupedCodes;
  private int repeatSize=0;
  private boolean isItExecutable;
  private UserVariableHandler handler;
  private Command variableCommand;
  private String name;
  private Command group;
  private List<String> commandsToAddToStack;

  public Repeat(UserVariableHandler handler, Command num, Command group) {
    this.handler=handler;
    variableCommand=num;
    this.group=group;
    commandsToAddToStack=new ArrayList<>();
    isItExecutable=false;
  }

  @Override
  public Object execute() {

    this.groupedCodes= (List<String>) group.execute();

    Object number=variableCommand.execute();
    Class<?> result=number.getClass();
    String className = (((Class) result).getName().split("[.]"))[result.getName().split("[.]").length - 1];

    if(className.equals("String")&&handler.getKeys().contains(((String)number))){
      this.repeatSize= handler.getVariable((String)number).getValue().intValue();
    } else if(className.equals("Double")){
      this.repeatSize=  ((Double) number).intValue();
    } else{
      isItExecutable=true;// return zero
    }

    if(isItExecutable){
      return 0.0;
    }
    cleanTheFirstLayerBrackets();
    repeatCommands();

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
