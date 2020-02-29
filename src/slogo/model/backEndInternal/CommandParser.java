package slogo.model.backEndInternal;

import slogo.model.CommandHandler;
import slogo.model.ExecutionException;
import slogo.model.backEndInternal.commands.Command;
import slogo.model.InvalidCommandException;
import slogo.model.Parser;
import slogo.model.backEndInternal.commands.Repeat;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Pattern;

public class CommandParser implements Parser {

    private static final String RESOURCES_PACKAGE = "resources.languages.";

    private ResourceBundle sizes = ResourceBundle.getBundle(RESOURCES_PACKAGE + "ArgumentSize");

    private List<Map.Entry<String, Pattern>> mySymbols;

    private Stack<Command> argumentStack = new Stack<>();
    private Stack<String> commandStack = new Stack<>();

    private List<String> commandList = new ArrayList<>();
    private Map<String, Runnable> matchMethodsToRun;

    private CommandFactory commandFactor;
    private CommandExecutor executor;
    private Integer commandCounter = 0;
    private CommandHandlerAPI commandHandler;
    private UserVariableHandler userVariableHandler;
    private int numOfCommandsToExecute=0;
    private BackEndTurtle turtle;
    private String currentCommand;

    private String LEFT_BRACKET = "[";
    private String RIGHT_BRACKET = "]";
    private String LEFT_PAR = "(";
    private String RIGHT_PAR = ")";

    private int leftBracketCounter=0;
    private int rightBracketCounter=0;

    /**
     * Create an empty parser
     */

    public CommandParser(CommandHandlerAPI commandHandler, UserVariableHandler userVariableHandler,
                         BackEndTurtle turtle) {
        this.commandHandler = commandHandler;
        this.userVariableHandler = userVariableHandler;
        this.turtle=turtle;
        mySymbols = new ArrayList<>();
        commandFactor = new CommandFactory(turtle, userVariableHandler, commandList, commandCounter);
        matchMethodsToRun = new HashMap<>();
        executor = new CommandExecutor();

        matchMethodsToRun.put("Constant", this::parseConstant);
        matchMethodsToRun.put("Command", this::parseName);
        matchMethodsToRun.put("Variable", this::parseVariable);
        matchMethodsToRun.put("ListStart", this::parseListStart);
        matchMethodsToRun.put("ListEnd", this::parseListEnd);
       matchMethodsToRun.put("Whitespace", this::parseWhiteSpace);
       matchMethodsToRun.put("Newline", this::parseNewLine);
       matchMethodsToRun.put("GroupEnd", this::parseGroupEnd);
       matchMethodsToRun.put("GroupStart", this::parseGroupStart);
    }

    private void parseListStart() {
        leftBracketCounter=0;
        rightBracketCounter=0;

        //getSymbol(commandStack.pop());
       leftBracketCounter++;
       List<String> group=new ArrayList<>();

       while(leftBracketCounter>rightBracketCounter){
           group.add(commandStack.pop());
           if(commandStack.peek().equals(RIGHT_BRACKET)){
               rightBracketCounter++;
           } else if(commandStack.peek().equals(LEFT_BRACKET)){
               leftBracketCounter++;
           }
           if(leftBracketCounter==rightBracketCounter){
               group.add(commandStack.pop());
               break;
           }



       }

    }

    private void parseListEnd(){
        throw  new InvalidCommandException();
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
    private  void parseGroupEnd(){
        throw  new InvalidCommandException();
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
        String currentCommand=getSymbol(commandStack.pop());

        Command com = null;
        //com = (Command) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);

        try {
            com = (Command) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException |
                ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        argumentStack.add(com);
    }

    private void parseName() {


    }

    private void buildExecutableCommand() throws ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {

        currentCommand=getSymbol(commandStack.pop());

        int numOfArguments=readArgumentSize(currentCommand);

        List<Object> argumentsToBuildCommand=new ArrayList<>();

        for(int i=0; i<numOfArguments; i++){
          if(argumentStack.size()==0){
              throw new InvalidCommandException();
          } else{
              //argumentStack.peek().execute();
              argumentsToBuildCommand.add(argumentStack.pop());

          }
        }


        Command com = (Command) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);
        argumentStack.add(com);


    }




    private void parseConstant() throws InvalidCommandException{

        List<Object> argumentsToBuildCommand= new ArrayList<>();
        argumentsToBuildCommand.add(commandStack.peek());
        String currentCommand=getSymbol(commandStack.pop());

        Command com = null;
        //com = (Command) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);

        try {
            com = (Command) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException |
                ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        argumentStack.add(com);
    }



    @Override
    public void parseCode(String consoleInput) throws InvalidCommandException, ExecutionException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //clearAll();
// should store the commands

        String[] lines=consoleInput.split("[\r\n]+");
        StringBuilder com=new StringBuilder();
        for(String str: lines){
            if(str.contains("#")){
               int index= str.indexOf("#");
                com.append(str, 0, index);

            } else{
                com.append(str);
            }
        }
        consoleInput=com.toString();
        consoleInput.replaceAll("[\r\n]+", " ");

        commandStack.addAll(Arrays.asList(consoleInput.split(" ")));

        while(commandStack.size()!=0){


            if(matchMethodsToRun.containsKey(getSymbol(commandStack.peek()))){ // if not actual command
                matchMethodsToRun.get(getSymbol(commandStack.peek())).run();
            } else {
                buildExecutableCommand(); // if it is command like for and repeat
            }

            //System.out.println("Value is "+(commandStack.peek()));
        }

        while(argumentStack.size()!=0){
            //System.out.println("Answer "+argumentStack.peek().execute());
           System.out.println("Answer is "+argumentStack.pop().execute());
        }


    }


    /**
     * @param syntax the name of the syntax source language name
     *               Adds the keys to mySymbols, thus comparison can be done
     */
    public void addPatterns(String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(syntax);
        for (String key : Collections.list(resources.getKeys())) {
            String regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key,
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }


    /**
     * @param command the commands as a text
     * @return the commands type
     */
    public String getSymbol(String command) {
        final String ERROR = "NO MATCH";
        System.out.println("INVALID: " + command + "is " + command);
        for (Map.Entry<String, Pattern> e : mySymbols) {
            if (match(command, e.getValue())) {
                return e.getKey();
            }
            //System.out.println("Availabale commands " +e.getValue());
        }
        // FIXME: perhaps throw an exception instead

        return ERROR;
    }

    private boolean match(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

    private int readArgumentSize(String key) {
        System.out.println("Key to be checked "+key);

        if(!sizes.containsKey(key)){
            clearAll();
        }


        return Integer.parseInt(sizes.getString(key));

    }

    private void clearAll(){
        commandList.clear();
        commandStack.clear();
        argumentStack.clear();
        commandCounter=0;
        numOfCommandsToExecute=0;
        commandFactor = new CommandFactory(turtle, userVariableHandler, commandList, commandCounter);

    }


}
