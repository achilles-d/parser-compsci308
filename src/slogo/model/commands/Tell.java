package slogo.model.commands;


import slogo.controller.TurtleController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Included in masterpiece to show that this is an example of non-executable code. This just changes the number of
 * active turtles (or creates one with indexes if needed) and there is no result needed to be added to stack.
 */
public class Tell implements Command{

    private TurtleController turtleController;
    private List<String> commands;
    private  List<Integer> intIndexes;
    private Command cnd;

    /**
     * command constructor
     * @param t turtle controller used
     * @param ind indexes
     */
    public Tell(TurtleController t,Command ind) {
        this.commands = new ArrayList<>();
        this.cnd=ind;
        this.turtleController = t;
    }

    /**
     * Execution logic
     * @return Double value argument
     */
    @Override
    public Object execute() {
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
        return Arrays.asList(intIndexes.get(intIndexes.size()-1).toString());
    }

    /**
     * Check if executable
     * @return Is it an executable command or not.
     */
    @Override
    public boolean isItExecutable() {
        return false;
    }
}

