//package slogo.model.backEndInternal.commands;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Stack;
//
//public class DoTimes implements Command<Double> {
//
//  Integer numOfTimes;
//  Stack<Command> commandStack;
//  Stack<Command> argumentStack;
//
//  public DoTimes(Integer n, Stack<Command> comStack, Stack<Command> argStack, ) {
//    this.numOfTimes = n;
//    this.commandStack = comStack;
//    this.argumentStack = argStack;
//  }
//
//  @Override
//  public void execute() {
//    List<Command> allTheCommands = new ArrayList<>();
//    List<Command> allTheArgs = new ArrayList<>();
//
//    for (int i = 0; i < numOfTimes; i++) {
//      allTheCommands.add("COMMAND_NAME");
//      allTheCommands.add("ALL_ARGUMENTS");
//    }
//
//    commandStack.addAll(allTheCommands);
//    argumentStack.addAll();
//
//  return 0.0;
//  }
//}
