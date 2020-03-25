package slogo.model.parsers;
import slogo.controller.Language;
import slogo.controller.TurtleController;
import slogo.model.parsers.subparsers.Symbol;
import slogo.model.turtle.BackEndTurtle;
import slogo.model.turtle.CommandExecutor;
import slogo.model.turtle.UserVariableHandler;
import slogo.model.commands.Command;
import slogo.model.exceptions.InvalidCommandException;
import slogo.model.interfaces.Parser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CommandParser implements Parser {
    private static final String RESOURCES_PACKAGE="resources.modelproperties.";
    private ResourceBundle sizes = ResourceBundle.getBundle(RESOURCES_PACKAGE + "ArgumentSize");
    private ResourceBundle methods = ResourceBundle.getBundle(RESOURCES_PACKAGE + "Methods");
    private ResourceBundle errors = ResourceBundle.getBundle(RESOURCES_PACKAGE + "ExceptionMessage");

    private Stack<Command> argumentStack = new Stack<>();
    private  Stack<String> commandStack = new Stack<>();
    private Map<String, List<List<Command>>> userDefined;

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
    private String HASH="#";
    private String SPACE=" ";
    private String NULL_CHARACTER="";
    private int INITIALIZER=0;
    private String UNMATHCHED="unmatched";
    private String LOW_INPUTS="inputs";
    private String NO_METHOD="noMethod";
    private String IMPOSSIBLE_COMMAND="impossibleCommand";

    private int leftBracketCounter=INITIALIZER;
    private int rightBracketCounter=INITIALIZER;
    private TurtleController turtleController;

    private Double output;
    private Symbol symbol;

    /**
     * The purpose of this class is to parse input commands and build the command tree and returning the final result
     * of the executed commands
     * @param commandHandler stores all the commands
     * @param userVariableHandler stores all the variables
     * @param tr turtle controller
     * @author Abebe Amare and Achintya
     */

    public CommandParser(CommandHandlerAPI commandHandler, UserVariableHandler userVariableHandler,
                         TurtleController tr) {

        this.commandHandler = commandHandler;
        this.userVariableHandler = userVariableHandler;
        turtleController=tr;
        userDefined=new HashMap<>();
        match=new HashMap<>();
        mathMethods();
        executor = new CommandExecutor();
    }

    private void mathMethods() {
        for(String str:methods.keySet()){
            Class[] parameterType = null;
            try {
                Method method=this.getClass().getDeclaredMethod(methods.getString(str),parameterType);
                match.put(str, method);
            } catch (NoSuchMethodException e) {
                throw new InvalidCommandException(errors.getString(NO_METHOD),e);
            }
        }
    }

    public void addPatterns(String language, String syntax){
        symbol=new Symbol(language, syntax);
        String lan = (language.split("[.]"))[language.split("[.]").length - 1];
        commandFactor = new CommandFactory(turtleController, userVariableHandler,
                Language.valueOf(lan.toUpperCase()), userDefined);
    }

    private void parseListEnd() {
        leftBracketCounter=INITIALIZER;
        rightBracketCounter=INITIALIZER;
        List<Object> argumentsToBuildCommand= new ArrayList<>();
        String currentCommand=symbol.getSymbol(commandStack.peek());
        List<Command> com = null;
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
        } catch (InvalidCommandException e) {
            throw new InvalidCommandException(errors.getString(UNMATHCHED));
        }
    argumentStack.addAll(com);

    }

    private void parseListStart(){
        commandStack.pop();
    }

    private void parseGroupStart(){
        leftBracketCounter=INITIALIZER;
        rightBracketCounter=INITIALIZER;
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
        throw new InvalidCommandException(errors.getString(UNMATHCHED));
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

        List<Command> com = null;
        if(userVariableHandler.getKeys().contains(variableName)){
            commandStack.add(Integer.toString(userVariableHandler.getVariable(variableName).getValue().intValue()))   ;
            parseConstant();
        } else{

            try {
                com = (List<Command>) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);
            } catch (InvalidCommandException e) {
                throw e;
            }
            argumentStack.addAll(com);
        }
    }

    private void parseName() {
        List<Object> argumentsToBuildCommand= new ArrayList<>();
        String currentCommand;

        if(userDefined.containsKey(commandStack.peek())){

            //int size=sizeOfUserDefinedCommand.get(commandStack.peek());
            int size=((List<String>)(userDefined.get(commandStack.peek()).get(1).get(0).execute())).size()-2;
            currentCommand="UserDefined";
            argumentsToBuildCommand.add(commandStack.peek());
            while(size!=0){
                //argumentsToBuildCommand.add(argumentStack.pop());
                userDefined.get(commandStack.peek()).get(0).add(argumentStack.pop());
                size--;
            }
        } else {
            currentCommand="StringName";
            argumentsToBuildCommand.add(commandStack.peek());
        }
        List<Command> com = null;
        try {
            com = (List<Command>) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);

        } catch (InvalidCommandException e) {
            throw e;
        }
        argumentStack.addAll(com);
        commandStack.pop();
    }

    private void buildExecutableCommand()  {

        String currentCommand = symbol.getSymbol(commandStack.pop());
        int numOfArguments=readArgumentSize(currentCommand);
        List<Object> argumentsToBuildCommand=new ArrayList<>();
        for(int i=0; i<numOfArguments; i++){
          if(argumentStack.size()==0){
              throw new InvalidCommandException(errors.getString(LOW_INPUTS)+currentCommand, null);
          } else{
              argumentsToBuildCommand.add(argumentStack.pop());

          }
        }

        List<Command> com = (List<Command>) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);
        argumentStack.addAll(com);
    }

    private void parseConstant(){

        List<Object> argumentsToBuildCommand= new ArrayList<>();
        argumentsToBuildCommand.add(commandStack.peek());
        String currentCommand=symbol.getSymbol(commandStack.pop());

        List<Command> com = null;
        try {
            com = (List<Command>) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);
        } catch (InvalidCommandException e) {
            throw e;
        }
        argumentStack.addAll(com);
    }

    @Override
    public Double parseCode(String consoleInput)  {
        clearAll();
        commandHandler.updateCommandHistory(consoleInput);
        consoleInput = getCommandWithNoComment(consoleInput);
        fillStackWithValidCommand(consoleInput);

        buildAndExecuteCommand();
        return output;
    }

    private void buildAndExecuteCommand() {
        numOfCommandsToExecute++;
        while(commandStack.size()!=0){
            if(match.containsKey(symbol.getSymbol(commandStack.peek()))){
                try {
                    match.get(symbol.getSymbol(commandStack.peek())).invoke(this, null);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw  new InvalidCommandException(errors.getString(IMPOSSIBLE_COMMAND),e);
                   // e.printStackTrace();
                }
            } else {
                buildExecutableCommand();
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
        List<String> commandList= Arrays.asList(consoleInput.split(SPACE));
        for(String str: commandList){
                str= str.replaceAll("\\p{Blank}",NULL_CHARACTER);
                str=str.replaceAll("\\s+",NULL_CHARACTER);
                if(!str.equals(NULL_CHARACTER)){
                    commandStack.add(str);
                }
        }
    }

    private String getCommandWithNoComment(String consoleInput) {
        String[] lines=consoleInput.split("[\r\n]+");
        StringBuilder com=new StringBuilder();
        for(String str: lines){
            if(str.contains(HASH)){
                int index= str.indexOf(HASH);
                com.append(str, 0, index).append(SPACE);
            } else{
                com.append(str).append(SPACE);
            }
        }


        return com.toString().trim();
    }

    private int readArgumentSize(String key) {
        return Integer.parseInt(sizes.getString(key));

    }
    private void clearAll(){
        commandList.clear();
        commandStack.clear();
        argumentStack.clear();
        userDefined.clear();
    }

}
