package slogo.model.parsers;

import slogo.model.exceptions.NoSuchMethodException;
import slogo.model.parsers.subparsers.Symbol;
import slogo.model.turtle.BackEndTurtle;
import slogo.model.turtle.CommandExecutor;
import slogo.model.turtle.UserVariableHandler;
import slogo.model.exceptions.ExecutionException;
import slogo.model.commands.Command;
import slogo.model.exceptions.InvalidCommandException;
import slogo.model.interfaces.Parser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

public class CommandParser implements Parser {
    private static final String RESOURCES_PACKAGE="resources.modelproperties.";
    private ResourceBundle sizes = ResourceBundle.getBundle(RESOURCES_PACKAGE + "ArgumentSize");
    private ResourceBundle methods = ResourceBundle.getBundle(RESOURCES_PACKAGE + "Methods");

    private List<Map.Entry<String, Pattern>> mySymbols;

    private Stack<Command> argumentStack = new Stack<>();
    private  Stack<String> commandStack = new Stack<>();

    private List<String> commandList = new ArrayList<>();
    private Map<String, Method> match;

    private CommandFactory commandFactor;
    private CommandExecutor executor;
    private CommandHandlerAPI commandHandler;
    private UserVariableHandler userVariableHandler;
    private int numOfCommandsToExecute=0;
    private BackEndTurtle turtle;

    private String LEFT_BRACKET = "[";
    private String RIGHT_BRACKET = "]";
    private String LEFT_PAR = "(";
    private String RIGHT_PAR = ")";

    private int leftBracketCounter=0;
    private int rightBracketCounter=0;

    private Double output;

    private Symbol symbol;

    /**
     * Create an empty parser
     */

    public CommandParser(CommandHandlerAPI commandHandler, UserVariableHandler userVariableHandler,
                         BackEndTurtle turtle) throws ExecutionException, java.lang.NoSuchMethodException {
        this.commandHandler = commandHandler;
        this.userVariableHandler = userVariableHandler;
        this.turtle=turtle;
        mySymbols = new ArrayList<>();
        Integer commandCounter = 0;

        commandFactor = new CommandFactory(turtle, userVariableHandler, commandList, commandCounter);
        match = new HashMap<>();
        match=new HashMap<>();

        mathMethods();
        executor = new CommandExecutor();
    }

    private void mathMethods() throws java.lang.NoSuchMethodException {
        for(String str:methods.keySet()){
            match.put(str, this.getClass().getDeclaredMethod(methods.getString(str)));
        }
    }


    public void addPatterns(String language, String syntax){
        symbol=new Symbol(language, syntax); // comes from the controller
    }

    private void parseListEnd() {
        
        leftBracketCounter=0;
        rightBracketCounter=0;
        List<Object> argumentsToBuildCommand= new ArrayList<>();
        String currentCommand=symbol.getSymbol(commandStack.peek());

        Command com = null;

        rightBracketCounter++;
        while(leftBracketCounter<rightBracketCounter){
            argumentsToBuildCommand.add(0,commandStack.pop());
            if(commandStack.peek().equals(RIGHT_BRACKET)){
                rightBracketCounter++;
            } else if(commandStack.peek().equals(LEFT_BRACKET)){
                leftBracketCounter++;
            }
            if(leftBracketCounter==rightBracketCounter){
                argumentsToBuildCommand.add(0,commandStack.pop());
                break;
            }

        }
        try {
            com = (Command) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);
        } catch (InvalidCommandException e) {
            throw e;
        }
    argumentStack.add(com);

    }

    private void parseListStart(){
        throw new InvalidCommandException(""); //FIXME define a more specific message
    }

    private void parseGroupStart(){
        leftBracketCounter=0;
        rightBracketCounter=0;
        //getSymbol(commandStack.pop());
        leftBracketCounter++;
        List<String> group=new ArrayList<>();

        while(leftBracketCounter>rightBracketCounter){
            group.add(commandStack.pop());
            if(commandStack.peek().equals(RIGHT_PAR)){
                rightBracketCounter++;
            } else if(commandStack.peek().equals(LEFT_PAR)){
                leftBracketCounter++;
            }
            if(leftBracketCounter==rightBracketCounter){
                group.add(commandStack.pop());

                break;
            }
        }

    }
    private  void parseGroupEnd() {
        throw new InvalidCommandException("Temp"); //FIXME define a more specific message
    }

    private void parseNewLine(){
        commandStack.pop();
    }

    private void parseWhiteSpace(){
        commandStack.pop();
    }


    private void parseVariable() throws InvalidCommandException{
        List<Object> argumentsToBuildCommand= new ArrayList<>();
        argumentsToBuildCommand.add(commandStack.peek());
        String variableName=commandStack.peek();
        String currentCommand=symbol.getSymbol(commandStack.pop());


        Command com = null;
        if(userVariableHandler.getKeys().contains(variableName)){
            commandStack.add(Integer.toString(userVariableHandler.getVariable(variableName).getValue().intValue()))   ;
            parseConstant();
        } else{

            try {
                com = (Command) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);
            } catch (InvalidCommandException e) {
                throw e;
            }
            argumentStack.add(com);
        }
    }


    private void parseName() {
        commandStack.pop();
    }

    //FIXME change throws clause
    private void buildExecutableCommand()  {

        String currentCommand = symbol.getSymbol(commandStack.pop());

        int numOfArguments=readArgumentSize(currentCommand);

        List<Object> argumentsToBuildCommand=new ArrayList<>();

        for(int i=0; i<numOfArguments; i++){
          if(argumentStack.size()==0){
              throw new InvalidCommandException("", null);  //FIXME define a Throwable cause

          } else{
              argumentsToBuildCommand.add(argumentStack.pop());

          }
        }

        Command com = (Command) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);

        argumentStack.add(com);
    }


    private void parseConstant() throws InvalidCommandException{

        List<Object> argumentsToBuildCommand= new ArrayList<>();
        argumentsToBuildCommand.add(commandStack.peek());
        String currentCommand=symbol.getSymbol(commandStack.pop());

        Command com = null;
        try {
            com = (Command) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);
        } catch (InvalidCommandException e) {
            throw e;
        }
        argumentStack.add(com);
    }



    @Override
    public Double parseCode(String consoleInput) throws InvalidCommandException, InvocationTargetException, IllegalAccessException {
        clearAll();
        commandHandler.updateCommandHistory(consoleInput);
        consoleInput = getCommandWithNoComment(consoleInput);
        fillStackWithValidCommand(consoleInput);
        buildAndExecuteCommand();
        return output;
    }

    private void buildAndExecuteCommand() throws InvocationTargetException, IllegalAccessException {
        numOfCommandsToExecute++;
        while(commandStack.size()!=0){

            if(match.containsKey(symbol.getSymbol(commandStack.peek()))){ // if not actual command
                match.get(symbol.getSymbol(commandStack.peek())).invoke(this);
            } else {
                buildExecutableCommand(); // if it is command like for and repeat
            }
        }

        while(argumentStack.size()!=0){
            if(argumentStack.peek().isItExecutable()){
                output = (Double) argumentStack.pop().execute();
            } else{
                commandStack.addAll((Collection<? extends String>) argumentStack.pop().execute());
               buildAndExecuteCommand();
            }

        }
    }

    private void fillStackWithValidCommand(String consoleInput) {
        List<String> commandList= Arrays.asList(consoleInput.split(" "));
        for(String str: commandList){
                str= str.replaceAll("\\p{Blank}","");
                str=str.replaceAll("\\s+","");
                if(!str.equals("")){
                    commandStack.add(str);
                }
        }
    }

    private String getCommandWithNoComment(String consoleInput) {
        String[] lines=consoleInput.split("[\r\n]+");
        StringBuilder com=new StringBuilder();
        for(String str: lines){
            if(str.contains("#")){
               int index= str.indexOf("#");
                com.append(str, 0, index).append(" ");

            } else{
                com.append(str).append(" ");
            }
        }
        consoleInput=com.toString().trim();
        return consoleInput;
    }

    private int readArgumentSize(String key) {
        return Integer.parseInt(sizes.getString(key));

    }
    private void clearAll(){
        commandList.clear();
        commandStack.clear();
        argumentStack.clear();
    }

}
