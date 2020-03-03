package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

import java.util.List;

public class SetPenColor implements Command<String> {

    BackEndTurtle myTurtle;
    Command color;

    public SetPenColor(BackEndTurtle t, Command c) {
        this.myTurtle = t;
        this.color = c;
    }

    @Override
    public String execute() {
        myTurtle.setPenColor((String) color.execute());
        return (String) color.execute();
    }

}
