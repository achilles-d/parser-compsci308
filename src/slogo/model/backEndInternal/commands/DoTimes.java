//package slogo.model.backEndInternal.commands;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class DoTimes implements Command<Double> {
//
//    private List<String> commandList;
//    private String LEFT_BRACKET = "[";
//    private String RIGHT_BRACKET = "]";
//    private int size;
//    private StringBuilder commands = new StringBuilder();
//    private Integer repeat;
//
//    private Command groupCmd;
//    private Command repeatCmd;
//
//
//    public DoTimes(Command group, Command repeat) {
//        this.groupCmd=group;
//        this.repeatCmd=repeat;
//    }
//
//    @Override
//    public Double execute() {
//
//        ArrayList<String> commandToRepeat = new ArrayList<>();
//
//        newCounter = commandList.indexOf("DoTimes");
//
//        if (commandList.get(newCounter + 2).equals(LEFT_BRACKET)) {
//            newCounter += 3;
//            updateCommands();
//
//            System.out.println(commands.toString());
//
//            updateList(commandToRepeat);
//
//            List<String> rightSide = commandList.subList(newCounter, size);
//            commandToRepeat.addAll(rightSide);
//            commandList = commandToRepeat;
//            return Double.MAX_VALUE;
//        } else {
//            return  0.0;
//        }
//    }
//
//    private void updateList(ArrayList<String> commandToRepeat) {
//        for (int i = 0 ; i < repeat; i++) {
//            String[] com = commands.toString().split(" ");
//            commandToRepeat.addAll(Arrays.asList(com));
//        }
//    }
//
//    private void updateCommands() {
//
//        while(newCounter < commandList.size()){
//            if (commandList.get(newCounter).equals(RIGHT_BRACKET)) {
//                newCounter++;
//                break;
//            }
//            commands.append(commandList.get(newCounter));
//            commands.append(" ");
//            newCounter++;
//        }
//    }
//
//    @Override
//    public List<String> updateRawCommands() {
//        return commandList;
//    }
//
//    @Override
//    public Integer updateCounter() {
//        return -1;
//    }
//}
