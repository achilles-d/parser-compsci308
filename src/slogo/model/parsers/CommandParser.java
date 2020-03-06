package slogo.model.parsers;

import slogo.controller.TurtleController;
import slogo.model.subparsers.Symbol;
import slogo.model.turtle.BackEndTurtle;
import slogo.model.turtle.CommandExecutor;
import slogo.model.turtle.UserVariableHandler;
import slogo.model.exceptions.ExecutionException;
import slogo.model.commands.Command;
import slogo.model.exceptions.InvalidCommandException;
import slogo.model.interfaces.Parser;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Pattern;

public class CommandParser implements Parser {

    private static final String RESOURCES_PACKAGE = "resources.languages.";
    private  TurtleController turtleController;

    private ResourceBundle sizes = ResourceBundle.getBundle(RESOURCES_PACKAGE + "ArgumentSize");


    private Stack<Command> argumentStack = new Stack<>();
    private  Stack<String> commandStack = new Stack<>();

    private List<String> commandList = new ArrayList<>();
    private Map<String, Runnable> matchMethodsToRun;

    private CommandFactory commandFactor;
    private CommandExecutor executor;
    private CommandHandlerAPI commandHandler;
    private UserVariableHandler userVariableHandler;
    private int numOfCommandsToExecute=0;

    private Map<String, List<Command>> userDefinedFunction;

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
                         TurtleController turtleController) {
        this.commandHandler = commandHandler;
        this.userVariableHandler = userVariableHandler;
        this.turtleController=turtleController;
        userDefinedFunction=new HashMap<>();
        commandFactor = new CommandFactory(turtleController, userVariableHandler, commandList,userDefinedFunction);
        matchMethodsToRun = new HashMap<>();
        executor = new CommandExecutor();

        matchMethodsToRun.put("Constant", this::parseConstant);
        matchMethodsToRun.put("Command", this::userDefined);
        matchMethodsToRun.put("Variable", this::parseVariable);
        matchMethodsToRun.put("ListStart", this::parseListStart);
        matchMethodsToRun.put("ListEnd", this::parseListEnd);
        matchMethodsToRun.put("Whitespace", this::parseWhiteSpace);
        matchMethodsToRun.put("Newline", this::parseNewLine);
        matchMethodsToRun.put("GroupEnd", this::parseGroupEnd);
        matchMethodsToRun.put("GroupStart", this::parseGroupStart);
    }

public void  addPatterns(String language, String syntax){
    symbol=new Symbol(language, syntax); // comes from the controller
}



    private void parseListEnd() {
        leftBracketCounter=0;
        rightBracketCounter=0;
        List<Object> argumentsToBuildCommand= new ArrayList<>();
        String currentCommand=symbol.getSymbol(commandStack.peek());
        List<Command> com = new ArrayList<>();

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
            com = (List<Command>) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException
                | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    argumentStack.addAll(com);

    }

    private void parseListStart(){
        throw new InvalidCommandException("");
    }

    private void parseGroupStart(){
        leftBracketCounter=0;
        rightBracketCounter=0;
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
        throw new InvalidCommandException("");

    }

    private void parseNewLine(){
        commandStack.pop();
    }

    private void parseWhiteSpace(){
        commandStack.pop();
    }


    private void parseVariable(){
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
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException |
                    ClassNotFoundException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            argumentStack.add(com);
        }
    }

    private void userDefined() {
        List<Object> argumentsToBuildCommand= new ArrayList<>();
        String commandName;
        if(userDefinedFunction.containsKey(commandStack.peek())){

            List<Command> values= new ArrayList<>();

            int sizeOfArgument=((List<String>)userDefinedFunction.get(commandStack.peek()).get(0).execute()).size()-2;

       while(sizeOfArgument>0){
        if(argumentStack.size()==0){
            throw new InvalidCommandException("User Defined Function does not have enough arguments.");
        }
        values.add(argumentStack.pop());
        sizeOfArgument--;
    }
    commandName="UserDefined";
    argumentsToBuildCommand.add(values);
    argumentsToBuildCommand.add(userDefinedFunction.get(commandStack.peek()).get(0));//, userDefinedFunction.get(1));
     argumentsToBuildCommand.add(userDefinedFunction.get(commandStack.peek()).get(1));

        } else{
            commandName="StringName";
            argumentsToBuildCommand.add(commandStack.peek());
        }

        Command com = null;
        try {
            com = (Command) commandFactor.getCommand(commandName,argumentsToBuildCommand);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException |
                ClassNotFoundException | NoSuchMethodException e) {
            throw new InvalidCommandException(""+commandName);
        }
        argumentStack.add(com);
        commandStack.pop();

    }

    private void buildExecutableCommand() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        String currentCommand = symbol.getSymbol(commandStack.pop());

        int numOfArguments=readArgumentSize(currentCommand);
        List<Object> argumentsToBuildCommand=new ArrayList<>();

        for(int i=0; i<numOfArguments; i++){
          if(argumentStack.size()==0){
              throw new InvalidCommandException("Enough arguments is not given for "+currentCommand);

          } else{
              argumentsToBuildCommand.add(argumentStack.pop());

          }
        }

        List<Command> com = (List<Command>) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);

        argumentStack.addAll(com);


    }


    private void parseConstant() throws InvalidCommandException{

        List<Object> argumentsToBuildCommand= new ArrayList<>();
        argumentsToBuildCommand.add(commandStack.peek());
        String currentCommand=symbol.getSymbol(commandStack.pop());

        List<Command> com = new ArrayList<>();
        try {
            com = (List<Command>) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException |
                ClassNotFoundException | NoSuchMethodException e) {
            throw new InvalidCommandException("Default");
        }
        argumentStack.addAll(com);
    }



    @Override
    public Double parseCode(String consoleInput) throws InvalidCommandException, ExecutionException,
            ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        clearAll();
        consoleInput = getCommandWithNoComment(consoleInput);
        System.out.println(" string  |"+consoleInput+"| then this");
        fillStackWithValidCommand(consoleInput);

        buildAndExecuteCommand();
        return output;
    }

    private void buildAndExecuteCommand() throws ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        numOfCommandsToExecute++;
        System.out.println("step 1 in the loop " +numOfCommandsToExecute);
        while(commandStack.size()!=0){

            if(matchMethodsToRun.containsKey(symbol.getSymbol(commandStack.peek()))){ // if not actual command
                matchMethodsToRun.get(symbol.getSymbol(commandStack.peek())).run();
            } else {
                buildExecutableCommand(); // if it is command like for and repeat
            }
        }

        while(argumentStack.size()!=0){
            if(argumentStack.peek().isItExecutable()){
                output = (Double) argumentStack.pop().execute();
            } else{
                Object com=argumentStack.pop().execute();
                if(com instanceof String){
                    commandStack.add((String)com);
                } else{
                    commandStack.addAll((Collection<? extends String>) com);
                }
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
