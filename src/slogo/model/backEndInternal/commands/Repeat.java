package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.UserVariableHandler;

import java.util.ArrayList;
import java.util.List;


public class Repeat implements Command<Object> {

  private List<String> groupedCodes;
  private int repeatSize=0;
  private boolean isItExecutable;
  private UserVariableHandler handler;
  private Command variableCommand;
  private String name;

  private List<String> commandsToAddToStack;

  public Repeat(UserVariableHandler handler, Command num, Command group) {
    this.handler=handler;
    this.groupedCodes= (List<String>) group.execute();
    variableCommand=num;

    commandsToAddToStack=new ArrayList<>();


  }

  private void checkExecutability(UserVariableHandler handler, Command num) {
    Class<?> result=num.execute().getClass();
    String className = (((Class) result).getName().split("[.]"))[result.getName().split("[.]").length - 1];
    if(className.equals("String") && handler.getKeys().contains(num.execute())){
      this.repeatSize= handler.getVariable((String) num.execute()).getValue().intValue();
      isItExecutable=false;
    } else if(className.equals("String") && !handler.getKeys().contains(num.execute())){
      name= (String) variableCommand.execute();
      isItExecutable=false;
    } else if(className.equals("Double")){
      this.repeatSize=  ((Double) num.execute()).intValue();
      isItExecutable=false;
    } else{
      isItExecutable=true;// return zero
    }
  }

  @Override
  public Object execute() {

    if(isItExecutable){
      return 0.0;
    }
    if(repeatSize!=0){
      cleanTheFirstLayerBrackets();
      repeatCommands();
    } else{
      commandsToAddToStack.add("repeat");
      //String name= (String) variableCommand.execute();
      commandsToAddToStack.add(name);
      commandsToAddToStack.addAll(groupedCodes);
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
    checkExecutability(handler, variableCommand);
    return isItExecutable;
  }

}
