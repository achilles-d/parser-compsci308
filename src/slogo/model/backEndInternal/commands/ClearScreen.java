package slogo.model.backEndInternal.commands;

import slogo.model.Coordinate;
import slogo.model.backEndInternal.BackEndTurtle;

import java.util.List;

public class ClearScreen implements Command<Double> {

    BackEndTurtle myTurtle;

    public ClearScreen(BackEndTurtle bT) {
        this.myTurtle = bT;
    }

    @Override
    public Double execute() {
        myTurtle.getLines().clear();
        Coordinate currentPos = myTurtle.getPosition();
        myTurtle.setPosition(new Coordinate());
        return Math.sqrt(Math.pow(currentPos.getXVal(), 2) + Math.pow(currentPos.getYVal(), 2));
    }


    @Override
    public boolean isItExecutable() {
        return true;
    }
}
