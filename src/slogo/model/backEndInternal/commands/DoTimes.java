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
//        this.groupCmd = group;
//        this.repeatCmd = repeat;
//    }
//
////    for [ :dist 1 110 1 ]
////            [
////    fd :dist
////    rt product :dist 3
////            ]
//
//
//    @Override
//    public Double execute() {
//
//        ArrayList<String> commandToRepeat = new ArrayList<>();
//
//
//    }
//
//
//    private void updateList(ArrayList<String> commandToRepeat) {
//        for (int i = 0; i < repeat; i++) {
//            String[] com = commands.toString().split(" ");
//            commandToRepeat.addAll(Arrays.asList(com));
//        }
//    }
//
//    private void updateCommands() {
//
//    }
//
//    @Override
//    public boolean isItExecutable() {
//        return false;
//    }
//}
