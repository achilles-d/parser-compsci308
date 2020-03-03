package slogo.controller;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


import slogo.model.Coordinate;
import slogo.model.exceptions.InvalidCommandException;
import slogo.model.Line;
import slogo.model.Variable;
import slogo.model.backEndInternal.*;
import slogo.model.backEndInternal.commands.Command;
import slogo.view.ColorPalette;

public class ParserController {

    private static final String SYNTAX = "resources.languages.Syntax";
    private BackEndTurtle myBackEndTurtle;
    private CommandParser myCommandParser;
    private CommandHandlerAPI myCommandHandlerAPI;
    private UserVariableHandler myUserVarHandler;
    private Language myLanguage;
    private ColorPalette myColorPalette;

    public ParserController(){
        myBackEndTurtle = new BackEndTurtle();
        myCommandHandlerAPI = new CommandHandlerAPI();
        myUserVarHandler = new UserVariableHandler();
        myCommandParser = new CommandParser(myCommandHandlerAPI, myUserVarHandler, myBackEndTurtle);
        myColorPalette = new ColorPalette();
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

    public void setColorPaletteIndex(int index, int r, int g, int b)
    {
        myColorPalette.setColor(index,r,g,b);
    }

    public ColorPalette getColorPalette()
    {
        return myColorPalette;
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

    //TODO remove magic vars.
    public void saveCommandHistory() throws IOException {
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
        String filename = "command_history" + df.format(new Date()) + ".txt";
        try {
            BufferedWriter commandWriter = new BufferedWriter(new FileWriter(filename));
            for (String command : getCommandHistory()) {
                commandWriter.write(command);
                commandWriter.newLine();
            }
            commandWriter.close();
        }
        catch(IOException ex){
            throw ex;
        }
    }
}
