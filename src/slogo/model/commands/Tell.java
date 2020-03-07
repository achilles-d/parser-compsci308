package slogo.model.commands;


import slogo.controller.TurtleController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tell implements Command{

    private TurtleController turtleController;
    private List<String> indexes;
    private  List<Integer> intIndexes;
    private Command cnd;

    public Tell(Command ind, TurtleController t) {
        this.indexes = new ArrayList<>();
        this.cnd=ind;
        this.turtleController = t;

    }

    @Override
    public Object execute() {

        this.indexes.addAll( (List<String>) cnd.execute());
        indexes.remove("[");
        indexes.remove("]");
        intIndexes = indexes.stream().map(Integer::parseInt).collect(Collectors.toList());

        for (Integer i : intIndexes) {

            if (!turtleController.containsKey(i)) {
                turtleController.createNewTurtle(i);
            }
            turtleController.getBackEndTurtle(i).setActiveTurtle(true);
        }


        return (double)intIndexes.get(intIndexes.size()-1);
    }

    @Override
    public boolean isItExecutable() {
        return false;
    }
}

