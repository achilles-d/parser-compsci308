package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

import java.util.List;

public class SetPenColor implements Command<String> {

    BackEndTurtle myTurtle;
    String color;

    public SetPenColor(BackEndTurtle t, String c) {
        this.myTurtle = t;
        this.color = c;
    }

    @Override
    public String execute() {
        myTurtle.setPenColor(color);
        return "";
    }

    @Override
    public List<String> updateRawCommands() {
        return null;
    }

    @Override
    public Integer updateCounter() {
        return null;
    }
}
