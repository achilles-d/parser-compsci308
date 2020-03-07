package slogo.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;

import javafx.beans.property.Property;
import slogo.model.turtle.Coordinate;


import slogo.view.components.ColorPalette;
import slogo.model.parsers.CommandHandlerAPI;
import slogo.model.parsers.CommandParser;
import slogo.model.turtle.BackEndTurtle;
import slogo.model.turtle.Line;
import slogo.model.turtle.UserVariable;
import slogo.model.turtle.UserVariableHandler;


public class ParserController {

    private static final String SYNTAX = "resources.languages.Syntax";
    private static final String ELEMENTORDER = "elementorder";
    private static final String IMAGES = "images";
    private static final String COLORS = "colors";

    private ResourceBundle configFile;

    private BackEndTurtle myBackEndTurtle;
    private CommandParser myCommandParser;
    private CommandHandlerAPI myCommandHandlerAPI;
    private UserVariableHandler myUserVarHandler;
    private Language myLanguage;
    private ColorPalette myColorPalette;
    private TurtleController myTurtleController;
    private CommandFileIO myCommandFileIO;
    private Double output;
    private String elementOrder;
    private String availableColors;
    private String availableImages;

    public ParserController(String config) {

        configFile = ResourceBundle.getBundle(config);
        myTurtleController = new TurtleController(getAvailableImagesFile());
        //NEED TO REPLACE THIS WITH A LIST OF BACKENDTURTLES
        myBackEndTurtle = myTurtleController.getBackEndTurtle(0);
        myCommandHandlerAPI = new CommandHandlerAPI();
        myUserVarHandler = new UserVariableHandler();
        myCommandParser = new CommandParser(myCommandHandlerAPI, myUserVarHandler, myTurtleController);
        myColorPalette = new ColorPalette(getAvailableColorsFile());
        myCommandFileIO = new CommandFileIO();
        setLanguage("ENGLISH");
    }


    public String getUIOrderFile()
    {
        return ("resources."+configFile.getString(ELEMENTORDER));
    }

    public String getAvailableImagesFile()
    {
        return ("resources."+configFile.getString(IMAGES));
    }

    private String getAvailableColorsFile()
    {
        return ("resources."+configFile.getString(COLORS));
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

    public void parseCode(String code)  {
       // code = code.replaceAll("[\r\n]+", " ");

        try{
            output = myCommandParser.parseCode(code);
        }
        catch(Exception exception){
            throw exception;
        }
    }

    public Double getReturn()
    {
        return output;
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
            varNamesAndValues.add(var + "=" + varValue);
        }
        return Collections.unmodifiableList(varNamesAndValues);
    }

    public Language getMyLanguage() { return myLanguage; }

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
        myCommandParser.addPatterns(myLanguage.getLanguageFile(), SYNTAX);
    }

    public void setVariable(String varName, double value)
    {
        myUserVarHandler.setVariable(varName,value);
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
