package slogo.model.backEndInternal.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import slogo.model.backEndInternal.UserVariable;
import slogo.model.backEndInternal.UserVariableHandler;

public class Repeat implements Command<Double> {

  private List<String> commandList;
  //private Double[] argumentArray;
  private String LEFT_BRACKET = "[";
  private String RIGHT_BRACKET = "]";
  private int size;
  private int repCount = 1;
  private StringBuilder commands = new StringBuilder();
  private Integer repeat;
  private Integer newCounter;

  public Repeat(List<String> sCom, Double repeat, Integer counter) {
    this.commandList = sCom;
    this.size = commandList.size();
    this.newCounter=counter;
    this.repeat=repeat.intValue();
  }

  @Override
  public Double execute() {

    ArrayList<String> commandToRepeat = new ArrayList<>();

    if (commandList.get(newCounter + 2).equals(LEFT_BRACKET)) {
      newCounter += 3;
      updateCommands();

      updateRepcountValues(commandToRepeat);

      List<String> rightSide = commandList.subList(newCounter, size);
      commandToRepeat.addAll(rightSide);
      commandList = commandToRepeat;
      return Double.MAX_VALUE;
    } else {
      return 0.0;
    }
  }

  private void updateRepcountValues(ArrayList<String> commandToRepeat) {
    ArrayList<Integer> repCount = repCount();

    for (int i = 0; i < repeat; i ++) {
      int repCountCounter = 0;
      String[] com = commands.toString().split(" ");
      for (int j = 0; j < com.length; j++) {
        if ((repCount.size()!=0) && j == repCount.get(repCountCounter) ) {
          commandToRepeat.add(Integer.toString(i));
          repCountCounter++;
        } else {
          commandToRepeat.add(com[j]);
        }
      }
    }
  }

  private void updateCommands() {

    while(newCounter < commandList.size()){
      if (commandList.get(newCounter).equals(RIGHT_BRACKET)) {
        newCounter++;
        break;
      }
      commands.append(commandList.get(newCounter));
      commands.append(" ");
      newCounter++;
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
    return commandList;
  }



  private ArrayList<Integer> repCount() {
    String[] arrayofS = commands.toString().split(" ");
    ArrayList<Integer> locs = new ArrayList<>();
    for (int i = 0; i < arrayofS.length; i++) {
      if (arrayofS[i].equals(":repcount")) {
        locs.add(i);
      }
    }
    return locs;
  }



}
