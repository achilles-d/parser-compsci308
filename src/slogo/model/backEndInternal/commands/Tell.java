package slogo.model.backEndInternal.commands;

import slogo.controller.TurtleController;

import java.util.List;
import java.util.stream.Collectors;

public class Tell implements Command{

    private TurtleController turtleController;
    private List<String> indexes;

    public Tell(Command i, TurtleController t) {
        this.indexes = (List<String>) i.execute();
        this.turtleController = t;
    }

    @Override
    public Object execute() {
        List<Integer> intIndexes = indexes.stream().map(Integer::parseInt).collect(Collectors.toList());

        for (Integer i : intIndexes) {
            if (!turtleController.containsKey(i)) {
                turtleController.createNewTurtle(i);
            }
            turtleController.getBackEndTurtle(i).setActiveTurtle(true);
        }
        return intIndexes.get(intIndexes.size()-1);
    }

    @Override
    public boolean isItExecutable() {
        return true;
    }
}
