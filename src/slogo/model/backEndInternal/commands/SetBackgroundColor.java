package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

import java.util.List;

public class SetBackgroundColor implements Command<String> {

    private BackEndTurtle myTurtle;
    private Command color;

    public SetBackgroundColor(BackEndTurtle t, Command c) {
        this.myTurtle = t;
        this.color = c;
    }

    @Override
    public String execute() {
        myTurtle.setBackgroundColor((String) color.execute());
        return (String) color.execute();
    }

    @Override
    public boolean isItExecutable() {
        return true;
    }
}
