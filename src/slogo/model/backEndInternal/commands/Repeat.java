package slogo.model.backEndInternal.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import slogo.model.backEndInternal.UserVariable;
import slogo.model.backEndInternal.UserVariableHandler;

public class Repeat implements Command<Double> {

  private List<String> commandList;
  private Double[] argumentArray;
  private String LEFT_BRACKET = "]";
  private String RIGHT_BRACKET = "[";
  private int size;
  private int repCount = 1;
  private StringBuilder commands = new StringBuilder();

  public Repeat(List<String> sCom, Double[] aArray) {
    this.commandList = sCom;
    this.argumentArray = aArray;
    this.size = commandList.size();
  }

  @Override
  public Double execute() {
    int repeat = argumentArray[0].intValue();
    int newCounter = argumentArray[1].intValue();

    ArrayList<String> commandToRepeat = new ArrayList<>();


    if (commandList.get(newCounter + 2).equals(LEFT_BRACKET)) {
      newCounter +=3;

      while(newCounter < commands.length()){

        if (commandList.get(newCounter).equals(RIGHT_BRACKET)) {
            newCounter++;
            break;
        }
        commands.append(commandList.get(newCounter));
        commands.append(" ");
        newCounter++;
      }

      ArrayList<Integer> repCount = repCount();

      for (int i = 0; i < repeat; i ++) {
        int repCountCounter = 0;
        String[] com = commands.toString().split(" ");
        for (int j = 0; j < com.length; j++) {
          if (j == repCount.get(repCountCounter)) {
            commandToRepeat.add(Integer.toString(i));
            repCountCounter++;
          } else {
            commandToRepeat.add(com[j]);
          }
        }
        //commandToRepeat.addAll(Arrays.asList(commands.toString().split(" ")));
      }

      List<String> rightSide = commandList.subList(newCounter, size);

      commandToRepeat.addAll(rightSide);

      commandList = commandToRepeat;

      argumentArray[1] = (double) 0;

      return Double.MAX_VALUE;

    } else {
      return 0.0;
    }
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
