package slogo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;



import java.util.List;

import slogo.model.Coordinate;
import slogo.model.InvalidCommandException;
import slogo.model.Line;
import slogo.model.backEndInternal.*;
import slogo.model.backEndInternal.commands.Command;

public class ParserController {

    private BackEndTurtle myBackEndTurtle;
    private CommandParser myCommandParser;
    private CommandHandlerAPI myCommandHandlerAPI;
    private UserVariableHandler myUserVarHandler;
    private Language myLanguage;

    public ParserController(){
        myBackEndTurtle = new BackEndTurtle();
        myCommandHandlerAPI = new CommandHandlerAPI();
        myCommandParser = new CommandParser(myCommandHandlerAPI);
        myUserVarHandler = new UserVariableHandler();
        myLanguage = Language.ENGLISH;
        myCommandParser.addPatterns(myLanguage.myPropertyDir);
    }

    //To be called by Visualization
    public void toggleVisibility() {

    }

    //To be called by Visualization
    public String displayError(Exception ex){
        return ex.getMessage();
    }

    public Coordinate getTurtlePosition() {
        return myBackEndTurtle.getPosition();
    }

    public void parseCode(String code) throws InvalidCommandException {
        try{
            myCommandParser.parseCode(code);
        }
        catch(InvalidCommandException exception){
            displayError(exception);
        }
    }

    public List<String> getCommandHistory() {
        List<String> commandStrings = new ArrayList<>();
        for(Command cmd : myCommandHandlerAPI.getCommandHistory()){
            commandStrings.add(cmd.toString());
        }
        return commandStrings;
    }

    public double getHeading() {
        return myBackEndTurtle.getHeading();
    }

    //TODO implement when Model is ready
    public List<Line> getLines() {
        return null;
    }

    public UserVariable getVariable(String varName) {
        return myUserVarHandler.getVariable(varName);
    }

    public List<String> getAllVariables(){
        return null;
    }

    public String getLanguage(){
        return myLanguage.toString();
    }

    public void setLanguage(String language){
        myLanguage = Language.valueOf(language);
        myCommandParser.addPatterns(myLanguage.myPropertyDir);
    }
}
