package slogo.model.commands;

import slogo.model.turtle.Coordinate;
import slogo.model.turtle.BackEndTurtle;

public class ClearScreen implements Command<Double> {

    BackEndTurtle myTurtle;

    /**
     * Command Constructor
     * @param bT backend turtle to apply it to
     */
    public ClearScreen(BackEndTurtle bT) {
        this.myTurtle = bT;
    }

    /**
     * Execution logic
     * @return Double value argument
     */
    @Override
    public Double execute() {
        Coordinate currentPos = myTurtle.getPosition();
        myTurtle.setPosition(new Coordinate());
        myTurtle.getLines().clear();
        return Math.sqrt(Math.pow(currentPos.getXVal(), 2) + Math.pow(currentPos.getYVal(), 2));
    }

    /**
     * Check if executable
     * @return Is it an executable command or not.
     */
    @Override
    public boolean isItExecutable() {
        return true;
    }
}
