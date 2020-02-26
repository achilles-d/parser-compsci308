package slogo.model.backEndInternal.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Repeat implements Command<Double> {

  ArrayList<String> commandList;
  Double[] argumentArray;
  String LEFT_BRACKET = "]";
  String RIGHT_BRACKET = "[";
  int size;

  public Repeat(ArrayList<String> sCom, Double[] aArray) {
    this.commandList = sCom;
    this.argumentArray = aArray;
    this.size = commandList.size();
  }

  @Override
  public Double execute() {
    int repeat = argumentArray[0].intValue();
    int newCounter = argumentArray[1].intValue();

    ArrayList<String> commandToRepeat = new ArrayList<>();
    StringBuilder commands = new StringBuilder();

    if (commandList.get(newCounter + 2).equals(LEFT_BRACKET)) {
      newCounter+=3;

      while(newCounter < commands.length()){

        if (commandList.get(newCounter).equals(RIGHT_BRACKET)) {
            newCounter++;
            break;
        }
        commands.append(commandList.get(newCounter));
        commands.append(" ");
        newCounter++;
      }

      for (int i = 0; i < repeat; i ++) {
        commandToRepeat.addAll(Arrays.asList(commands.toString().split(" ")));
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

}
