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

/**
 * A class acting as the main interface between the frontend and the backend
 * LogoVisualization has an instance of this class
 * @author Achilles Dabrowski
 */
public class Controller {

    private static final String RESOURCES_DIR_PREFIX = "resources.";
    private static final String SYNTAX = "resources.languages.Syntax";
    private static final String ELEMENTORDER = "elementorder";
    private static final String IMAGES = "images";
    private static final String COLORS = "colors";
    private static final String DEFAULT_LANG = "ENGLISH";
    private static final String VAR_NAME_VALUE_DELIMITER = "=";

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


    /**
     * Create a new instance of Controller.
     * @param config the String directory of the package within the resources package (exclude "resources.")
     *        containing command execution order preferences
     */
    public Controller(String config) {

        configFile = ResourceBundle.getBundle(config);
        myTurtleController = new TurtleController(getAvailableImagesFile());
        myBackEndTurtle = myTurtleController.getBackEndTurtle(0);
        myCommandHandlerAPI = new CommandHandlerAPI();
        myUserVarHandler = new UserVariableHandler();
        myCommandParser = new CommandParser(myCommandHandlerAPI, myUserVarHandler, myTurtleController);
        myColorPalette = new ColorPalette(getAvailableColorsFile());
        myCommandFileIO = new CommandFileIO();
        setLanguage(DEFAULT_LANG);
    }

    /**
     * Return the String value corresponding to the name of the resource file specifying the order that the UI windows should be created
     * @return the String value corresponding to the name of the resource file specifying the order that the UI windows should be created
     */
    public String getUIOrderFile()
    {
        return (RESOURCES_DIR_PREFIX+configFile.getString(ELEMENTORDER));
    }

    /**
     * Return the String value corresponding to the name of the imagePalette resource file being used
     * @return Return the String value corresponding to the name of the imagePalette resource file being used
     */
    public String getAvailableImagesFile()
    {
        return (RESOURCES_DIR_PREFIX+configFile.getString(IMAGES));
    }

    /**
     * Return the String value corresponding to the name of the pen trails color resource file being used
     * @return the String value corresponding to the name of the pen trails color resource file being used
     */
    private String getAvailableColorsFile()
    {
        return (RESOURCES_DIR_PREFIX+configFile.getString(COLORS));
    }

    /**
     * Return the message text of an Exception thrown during code parsing or execution
     * @param ex the Exception thrown during code parsing or execution
     * @return the message text of an Exception thrown during code parsing or execution
     */
    public String displayError(Exception ex){
        return ex.getMessage();
    }

    /**
     * Set the color at a specific index of the color palette by specifying RGB values
     * @param index the index of the color palette to be set to a new RGB value
     * @param r the red value of the desired color
     * @param g the green value of the desired color
     * @param b the blue value of the desired color
     */
    public void setColorPaletteIndex(int index, int r, int g, int b)
    {
        myColorPalette.setColor(index,r,g,b);
    }

    /**
     * Return the ColorPalette object currently in use
     * @return the ColorPalette object currently in use
     */
    public ColorPalette getColorPalette()
    {
        return myColorPalette;
    }

    /**
     * Parse the code entered by the user to prepare it for execution
     * @param code the String representation of the code entered by the user into the console
     */
    public void parseCode(String code)  {
        try{
            output = myCommandParser.parseCode(code);
        }
        catch(Exception exception){
            throw exception;
        }
    }

    /**
     * Return the result of the command(s) executed to make it possible to update the appropriate UI windows
     * @return the result of the command(s) executed to make it possible to update the appropriate UI windows
     */
    public Double getReturn()
    {
        return output;
    }

    /**
     * Return the commands previously executed
     * @return a List of Strings, where each String represents a previously executed command
     */
    public List<String> getCommandHistory() {
        List<String> commandStrings = new ArrayList<>();
        for(String cmd : myCommandHandlerAPI.getCommandHistory()){
            commandStrings.add(cmd);
        }
        return commandStrings;
    }


    /**
     * Return the current heading of the turtle in degrees
     * @return the current heading of the turtle in degrees
     */
    public double getHeading() {
        return myBackEndTurtle.getHeading();
    }

    /**
     * Return whether or not the turtle's pen is currently drawing or not
     * @return true if the turtle's pen is currently drawing and false if not
     */
    public Property<Boolean> getPenColorProperty(){return myBackEndTurtle.getPenVisibilityProperty();}

    /**
     * Return the Line objects currently in existence representing the lines drawn on the turtle window
     * @return the Line objects currently in existence representing the lines drawn on the turtle window
     */
    public List<Line> getLines() {
        return Collections.unmodifiableList(myBackEndTurtle.getLines());
    }

    /**
     * Return the UserVariable object corresponding to a desired user variable
     * @param varName a String that is the name of the desired variable
     * @return the UserVariable object corresponding to the name of the desired user variable
     */
    public UserVariable getVariable(String varName) {
        return myUserVarHandler.getVariable(varName);
    }

    /**
     * Return the names of all of the existing user variables
     * @return a list of Strings that are the names of the existing user variables
     */
    public List<String> getAllVariables(){
        List<String> variables = myUserVarHandler.getKeys();
        List<String> varNamesAndValues = new ArrayList<>();
        for(String var : variables){
            String varValue = myUserVarHandler.getVariable(var).toString();
            varNamesAndValues.add(var + VAR_NAME_VALUE_DELIMITER + varValue);
        }
        return Collections.unmodifiableList(varNamesAndValues);
    }

    /**
     * Return the current language in use
     * @return the Language enum representing the current language in use
     */
    public Language getMyLanguage() { return myLanguage; }

    /**
     * Return the resource file name corresponding to the current language in use
     * @return the resource file name corresponding to the current language in use
     */
    public String getLanguage(){
        return myLanguage.getLanguageFile();
    }

    /**
     * Return the current TurtleController object in use
     * @return the current TurtleController object in use
     */
    public TurtleController getTurtleController()
    {
        return myTurtleController;
    }

    /**
     * Set the coding language
     * @param language the name of the language to be used
     */
    public void setLanguage(String language){
        myLanguage = Language.valueOf(language.toUpperCase());
        myCommandParser.addPatterns(myLanguage.getLanguageFile(), SYNTAX);
    }

    /**
     * Set the value of a desired variable
     * @param varName the name of the desired variable
     * @param value the new value of the value
     */
    public void setVariable(String varName, double value)
    {
        myUserVarHandler.setVariable(varName,value);
    }

    /**
     * Save the command history displayed in the UI to a text file
     * @throws IOException if the file cannot be written
     */
    public void saveCommandHistory() throws IOException {
        try {
            myCommandFileIO.updateCommandHistory(getCommandHistory());
            myCommandFileIO.saveCommandHistory();
        }
        catch(IOException ex){
            throw ex;
        }
    }

    /**
     * Read and parse commands from a file
     * @param commandFile the file containing the commands to be parsed
     * @throws Exception if the file cannot be read
     */
    public void parseFileCode(File commandFile) throws Exception {
        try {
            parseCode(myCommandFileIO.readCommandFile(commandFile));
        }
        catch(Exception ex){
            throw ex;
        }
    }


}
