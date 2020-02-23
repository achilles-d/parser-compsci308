package slogo.controller;

import java.util.ArrayList;
import java.util.Collections;
import slogo.model.InvalidCommandException;
import slogo.model.Variable;

import java.awt.*;
import java.util.List;
import slogo.model.backEndInternal.BackEndTurtle;
import slogo.model.backEndInternal.CommandParser;
import slogo.model.backEndInternal.UserVariableHandler;

public class ParserController implements Controller{

    private BackEndTurtle myBackEndTurtle;
    private CommandParser myCommandParser;
    private UserVariableHandler myUserVarHandler;

    public ParserController(){
        myBackEndTurtle = new BackEndTurtle();
        myCommandParser = new CommandParser();
        myUserVarHandler = new UserVariableHandler();

    }

    public void updateViewTurtlePosition() {

    }

    public void updateTrails() {

    }

    public void toggleVisibility() {

    }

    public void clearScreen() {

    }

    public void displayError(Exception ex) {

    }

    public Point getTurtlePosition() {
        return new Point((int) myBackEndTurtle.getPosition().getXVal(), (int) myBackEndTurtle.getPosition().getYVal());
    }

    public double getTurtleHeading() {
        return 0.0;
    }

    public void parseCode(String code) {
        try{
            myCommandParser.parseCode(code);
        }
        catch(InvalidCommandException exception){
            displayError(exception);
        }
    }

    public List<String> getCommandHistory() {
        return null;
    }

    public double getHeading() {
        return myBackEndTurtle.getHeading();
    }

    public List<Point> getLines() {
        return null;
    }

    public Variable getVariable(String varName) {
        return myUserVarHandler.getVariable(varName);
    }

    public List<String> getAllVariables(){
        List<Variable> variables = myUserVarHandler.getAllVariables();
        List<String> varNames = new ArrayList<String>();
        for(Variable var : variables){
            varNames.add(var.toString());
        }
        return Collections.unmodifiableList(varNames);
    }
}
