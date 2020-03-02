package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

import java.util.List;

public class SetBackgroundColor implements Command<String> {

    private BackEndTurtle myTurtle;
    private String color;

    public SetBackgroundColor(BackEndTurtle t, String c) {
        this.myTurtle = t;
        this.color = c;
    }

    @Override
    public String execute() {
        myTurtle.setBackgroundColor(color);
        return color;
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
