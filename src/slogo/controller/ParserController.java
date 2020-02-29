package slogo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;



import java.util.List;

import slogo.model.Coordinate;
import slogo.model.InvalidCommandException;
import slogo.model.Line;
import slogo.model.Variable;
import slogo.model.backEndInternal.*;
import slogo.model.backEndInternal.commands.Command;

public class ParserController {

    private static final String SYNTAX = "resources.languages.Syntax";
    private BackEndTurtle myBackEndTurtle;
    private CommandParser myCommandParser;
    private CommandHandlerAPI myCommandHandlerAPI;
    private UserVariableHandler myUserVarHandler;
    private Language myLanguage;

    public ParserController(){
        myBackEndTurtle = new BackEndTurtle();
        myCommandHandlerAPI = new CommandHandlerAPI();
        myUserVarHandler = new UserVariableHandler();
        myCommandParser = new CommandParser(myCommandHandlerAPI, myUserVarHandler, myBackEndTurtle);
        //setLanguage("ENGLISH");
        setLanguage("ENGLISH");
    }

    //To be called by Visualization
    public String displayError(Exception ex){
        return ex.getMessage();
    }

    public Coordinate getTurtlePosition() {
        return myBackEndTurtle.getPosition();
    }

    public void parseCode(String code) throws Exception {
       // code = code.replaceAll("[\r\n]+", " ");
        try{
            myCommandParser.parseCode(code);
        }
        catch(Exception exception){
            throw exception;
        }
    }

    public List<String> getCommandHistory() {
        List<String> commandStrings = new ArrayList<>();
        for(String cmd : myCommandHandlerAPI.getCommandHistory()){
            commandStrings.add(cmd);
        }
        return commandStrings;
    }

    public boolean getTurtleVisibility()
    {
        return myBackEndTurtle.getVisibility();
    }
    public double getHeading() {
        return myBackEndTurtle.getHeading();
    }

    //TODO implement when Model is ready
    public List<Line> getLines() {
        return Collections.unmodifiableList(myBackEndTurtle.getLines());
    }

    public UserVariable getVariable(String varName) {
        return myUserVarHandler.getVariable(varName);
    }

    public List<String> getAllVariables(){
        List<String> variables = myUserVarHandler.getKeys();
        System.out.println(myUserVarHandler.getKeys().size());
        List<String> varNamesAndValues = new ArrayList<>();
        for(String var : variables){
            String varValue = myUserVarHandler.getVariable(var).toString();
            varNamesAndValues.add(var + ":" + varValue);
        }
        return Collections.unmodifiableList(varNamesAndValues);
    }

    public String getLanguage(){
        return myLanguage.getLanguageFile();
    }

    public void setLanguage(String language){
        myLanguage = Language.valueOf(language.toUpperCase());
        myCommandParser.addPatterns(myLanguage.getLanguageFile());
        myCommandParser.addPatterns(SYNTAX);
    }
}
