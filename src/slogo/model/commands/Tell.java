package slogo.model.commands;

import slogo.controller.TurtleController;

import java.util.List;
import java.util.stream.Collectors;

public class Tell implements Command{

    private TurtleController turtleController;
    private List<String> indexes;
   private  List<Integer> intIndexes;

    public Tell(Command ind, TurtleController t) {
        this.indexes = (List<String>) ind.execute();
        this.turtleController = t;
indexes.remove("[");
indexes.remove("]");
       intIndexes = indexes.stream().map(Integer::parseInt).collect(Collectors.toList());
        System.out.println("Indexes "+intIndexes);

        for (Integer i : intIndexes) {

            if (!turtleController.containsKey(i)) {
                turtleController.createNewTurtle(i);
            }
            turtleController.getBackEndTurtle(i).setActiveTurtle(true);
        }


    }

    @Override
    public Object execute() {

        return (double)intIndexes.get(intIndexes.size()-1);
    }

    @Override
    public boolean isItExecutable() {
        return true;
    }
}
