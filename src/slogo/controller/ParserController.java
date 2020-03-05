package slogo.controller;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javafx.beans.property.Property;
import slogo.model.Coordinate;
import slogo.model.backEndInternal.*;
import slogo.model.exceptions.InvalidCommandException;
import slogo.model.exceptions.ExecutionException;
import slogo.model.Line;
import slogo.model.Variable;



import slogo.view.ColorPalette;

public class ParserController {

    private static final String SYNTAX = "resources.languages.Syntax";
    private BackEndTurtle myBackEndTurtle;
    private CommandParser myCommandParser;
    private CommandHandlerAPI myCommandHandlerAPI;
    private UserVariableHandler myUserVarHandler;
    private Language myLanguage;
    private ColorPalette myColorPalette;
    private TurtleController myTurtleController;
    private CommandFileIO myCommandFileIO;
    private Double output;

    public ParserController() {
        myTurtleController = new TurtleController();
        //NEED TO REPLACE THIS WITH A LIST OF BACKENDTURTLES
        myBackEndTurtle = myTurtleController.getBackEndTurtle(0);
        myCommandHandlerAPI = new CommandHandlerAPI();
        myUserVarHandler = new UserVariableHandler();
        myCommandParser = new CommandParser(myCommandHandlerAPI, myUserVarHandler, myBackEndTurtle);
        myColorPalette = new ColorPalette();
        myCommandFileIO = new CommandFileIO();
        setLanguage("ENGLISH");
    }



    //To be called by Visualization
    public String displayError(Exception ex){
        return ex.getMessage();
    }

    public Coordinate getTurtlePosition() {
        return myBackEndTurtle.getPosition();
    }

    public void setColorPaletteIndex(int index, int r, int g, int b)
    {
        myColorPalette.setColor(index,r,g,b);
    }

    public ColorPalette getColorPalette()
    {
        return myColorPalette;
    }

    public Double parseCode(String code) throws Exception {
       // code = code.replaceAll("[\r\n]+", " ");
        try{
            output = myCommandParser.parseCode(code);
            return output;
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

    public Property<Boolean> getPenColorProperty(){return myBackEndTurtle.getPenVisibilityProperty();}

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

    public TurtleController getTurtleController()
    {
        return myTurtleController;
    }

    public void setLanguage(String language){
        System.out.println("called langauge button");
        myLanguage = Language.valueOf(language.toUpperCase());
        myCommandParser.addPatterns(myLanguage.getLanguageFile());
        myCommandParser.addPatterns(SYNTAX);
    }

    public void saveCommandHistory() throws IOException {
        try {
            myCommandFileIO.updateCommandHistory(getCommandHistory());
            myCommandFileIO.saveCommandHistory();
        }
        catch(IOException ex){
            throw ex;
        }
    }

    public void parseFileCode(File commandFile) throws Exception {
        try {
            parseCode(myCommandFileIO.readCommandFile(commandFile));
        }
        catch(Exception ex){
            throw ex;
        }
    }
}
