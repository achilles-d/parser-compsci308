package slogo.model.commands;

import slogo.model.turtle.Coordinate;
import slogo.model.turtle.BackEndTurtle;

public class ClearScreen implements Command<Double> {

    BackEndTurtle myTurtle;

    public ClearScreen(BackEndTurtle bT) {
        this.myTurtle = bT;
    }

    @Override
    public Double execute() {
        Coordinate currentPos = myTurtle.getPosition();
        myTurtle.setPosition(new Coordinate());
        myTurtle.getLines().clear();
        return Math.sqrt(Math.pow(currentPos.getXVal(), 2) + Math.pow(currentPos.getYVal(), 2));
    }


    @Override
    public boolean isItExecutable() {
        return true;
    }
}
