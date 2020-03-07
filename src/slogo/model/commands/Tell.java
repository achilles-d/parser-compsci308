package slogo.model.commands;


import slogo.controller.TurtleController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tell implements Command{

    private TurtleController turtleController;
    private List<String> commands;
    private  List<Integer> intIndexes;
    private Command cnd;

    public Tell(TurtleController t,Command ind) {
        System.out.println("Tell is coming here ");
        this.commands = new ArrayList<>();
        this.cnd=ind;
        this.turtleController = t;

    }

    @Override
    public Object execute() {

        System.out.println("reached tell "+commands.toString());

        this.commands.addAll( (List<String>) cnd.execute());
        commands.remove("[");
        commands.remove("]");
        intIndexes = commands.stream().map(Integer::parseInt).collect(Collectors.toList());

        for (Integer i : intIndexes) {

            if (!turtleController.containsKey(i)) {
                turtleController.createNewTurtle(i);
            }
            turtleController.getBackEndTurtle(i).setActiveTurtle(true);
        }
        System.out.println("Command reached here "+intIndexes.get(intIndexes.size()-1).toString());
        return Arrays.asList(intIndexes.get(intIndexes.size()-1).toString());
    }

    @Override
    public boolean isItExecutable() {
        return false;
    }
}

