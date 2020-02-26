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

    //this.argumentArray = aArray;
    this.size = commandList.size();
    this.newCounter=counter;
    this.repeat=repeat.intValue();
    System.out.println("Conter is "+newCounter);

  }

  @Override
  public Double execute() {

    ArrayList<String> commandToRepeat = new ArrayList<>();

    if (commandList.get(newCounter + 2).equals(LEFT_BRACKET)) {
      newCounter +=3;

      while(newCounter < commandList.size()){

        if (commandList.get(newCounter).equals(RIGHT_BRACKET)) {
            newCounter++;
            break;
        }
        commands.append(commandList.get(newCounter));
        commands.append(" ");
        newCounter++;
      }

      System.out.println("commands as string extracted "+ commands.toString());
      ArrayList<Integer> repCount = repCount();

      for (int i = 0; i < repeat; i ++) {
        int repCountCounter = 0;
        String[] com = commands.toString().split(" ");
        System.out.println("commands parsed length  "+ com.length);
        for (int j = 0; j < com.length; j++) {
          if ((repCount.size()!=0) && j == repCount.get(repCountCounter) ) {
            commandToRepeat.add(Integer.toString(i));
            repCountCounter++;
          } else {
            commandToRepeat.add(com[j]);
          }
        }
        //commandToRepeat.addAll(Arrays.asList(commands.toString().split(" ")));
      }
      System.out.println("after commands elongate"+ commandToRepeat.toString());

      List<String> rightSide = commandList.subList(newCounter, size);
      System.out.println("Right side left"+rightSide);
      commandToRepeat.addAll(rightSide);
      commandList = commandToRepeat;

      System.out.println("Command to runt next "+ commandList.toString());
      System.out.println("Command to runt next "+ commandList.size());

      newCounter=0;

      return Double.MAX_VALUE;

    } else {
      System.out.println("Command to runt next "+ commandList.toString());
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
