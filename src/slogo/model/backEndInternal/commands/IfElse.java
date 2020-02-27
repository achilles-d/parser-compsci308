package slogo.model.backEndInternal.commands;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IfElse implements Command<Double> {

    private List<String> commandList;
    private String LEFT_BRACKET = "[";
    private String RIGHT_BRACKET = "]";
    private StringBuilder commands = new StringBuilder();
    Integer check;
    Integer newCounter;
    private int size;

    public IfElse(List<String> sCom, Integer counter) {
        this.commandList = sCom;
        this.size = commandList.size();
        this.newCounter = counter;
        this.check = Integer.parseInt(commandList.get(1));
    }


    @Override
    public Double execute() {
        ArrayList<String> commandToRepeat = new ArrayList<>();

        System.out.println(commandList);
        List<String> intList = commandList.subList(commandList.lastIndexOf(LEFT_BRACKET), commandList.size());
        List<String> rightSide = intList.subList(intList.indexOf(RIGHT_BRACKET), intList.size());
        rightSide = rightSide.subList(1, rightSide.size());

        if (check != 0) {
            if (commandList.get(newCounter + 2).equals(LEFT_BRACKET)) {
                newCounter += 3;
                updateCommands();
                String[] com = commands.toString().split(" ");
                commandToRepeat.addAll(Arrays.asList(com));
                commandToRepeat.addAll(rightSide);
                commandList = commandToRepeat;
                System.out.println(commandList);
            }
            // True statements
        } else {
            newCounter = commandList.indexOf(RIGHT_BRACKET) + 1;
            if (commandList.get(newCounter).equals(LEFT_BRACKET)) {
                newCounter++;
                updateCommands();
                String[] com = commands.toString().split(" ");
                commandToRepeat.addAll(Arrays.asList(com));
                commandToRepeat.addAll(rightSide);
                commandList = commandToRepeat;
                System.out.println(commandList);
            }
            // False statements
        }
        return Double.MAX_VALUE;
    }

    @Override
    public List<String> updateRawCommands() {
        //System.out.println("ahdsajldfslskjjlkdsjlkadfs");
        //System.out.println(commandList);
        return commandList;
    }

    @Override
    public Integer updateCounter() {
        return -1;
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
}
