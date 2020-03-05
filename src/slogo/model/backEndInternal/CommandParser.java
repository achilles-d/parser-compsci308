package slogo.model.backEndInternal;

import slogo.model.exceptions.ExceptionFactory;
import slogo.model.exceptions.ExecutionException;
import slogo.model.backEndInternal.commands.Command;
import slogo.model.exceptions.InvalidCommandException;
import slogo.model.Parser;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Pattern;

public class CommandParser implements Parser {

    private static final String RESOURCES_PACKAGE = "resources.languages.";

    private ResourceBundle sizes = ResourceBundle.getBundle(RESOURCES_PACKAGE + "ArgumentSize");

    private List<Map.Entry<String, Pattern>> mySymbols;

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

    /**
     * Create an empty parser
     */

    public CommandParser(CommandHandlerAPI commandHandler, UserVariableHandler userVariableHandler,
                         BackEndTurtle turtle) {
        this.commandHandler = commandHandler;
        this.userVariableHandler = userVariableHandler;
        this.turtle=turtle;
        userDefinedFunction=new HashMap<>();

        mySymbols = new ArrayList<>();
        Integer commandCounter = 0;
        commandFactor = new CommandFactory(turtle, userVariableHandler, commandList,userDefinedFunction);
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

    private void parseListEnd() {
        leftBracketCounter=0;
        rightBracketCounter=0;
        List<Object> argumentsToBuildCommand= new ArrayList<>();
       //List<String> codeElements=new ArrayList<>();
        String currentCommand=getSymbol(commandStack.peek());
        System.out.println("Current comamnd is "+currentCommand);
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
        //System.out.println(" Added list is "+codeElements.toString());
       // argumentsToBuildCommand.add(codeElements);
        //argumentsToBuildCommand
        try {
            com = (Command) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException
                | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    argumentStack.add(com);

    }

    private void parseListStart(){
        throw new InvalidCommandException("");
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
        String currentCommand=getSymbol(commandStack.pop());

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
        System.out.println("UserrDefined function "+commandStack.peek());
        if(userDefinedFunction.containsKey(commandStack.peek())){

            List<Command> values= new ArrayList<>();

            int sizeOfArgument=((List<String>)userDefinedFunction.get(commandStack.peek()).get(0).execute()).size()-2;

            System.out.println("need size of values "+sizeOfArgument);
            System.out.println("name of commmand "+ commandStack.peek());

       while(sizeOfArgument>0){
        if(argumentStack.size()==0){
            throw new InvalidCommandException("User Defined Function does not have enough arguments.");
        }
        values.add(argumentStack.pop());
        sizeOfArgument--;
    }
    commandName="UserDefined";
    //System.out.println("values given from the user "+values.toString());
    //System.out.println("variables "+userDefinedFunction.get(commandStack.peek()).get(0).execute().toString());
    //System.out.println("Commands "+userDefinedFunction.get(commandStack.peek()).get(1).execute().toString());
    //System.out.println("Size of user defined function map "+userDefinedFunction.size());
    argumentsToBuildCommand.add(values);
    argumentsToBuildCommand.add(userDefinedFunction.get(commandStack.peek()).get(0));//, userDefinedFunction.get(1));
     argumentsToBuildCommand.add(userDefinedFunction.get(commandStack.peek()).get(1));

            //String currentCommand=getSymbol(commandStack.pop());

        } else{
            commandName="StringName";
            //System.out.println("should put this mame to the types "+commandStack.peek());
            argumentsToBuildCommand.add(commandStack.peek());
        }
        //argumentsToBuildCommand.add("UserDefined");

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

        String currentCommand = getSymbol(commandStack.pop());

       // System.out.println("Command to create in parser "+currentCommand);

        int numOfArguments=readArgumentSize(currentCommand);
//        System.out.println("Command type "+currentCommand);
//        System.out.println("Required argument size "+numOfArguments);
//        System.out.println("Argument stack size "+argumentStack.size());
        List<Object> argumentsToBuildCommand=new ArrayList<>();

        for(int i=0; i<numOfArguments; i++){
          if(argumentStack.size()==0){
              throw new InvalidCommandException("Enough arguments is not given for "+currentCommand);

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
        String currentCommand=getSymbol(commandStack.pop());

        Command com = null;
        try {
            com = (Command) commandFactor.getCommand(currentCommand,argumentsToBuildCommand);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException |
                ClassNotFoundException | NoSuchMethodException e) {
            throw new InvalidCommandException("Default");
        }
        argumentStack.add(com);
    }



    @Override
    public Double parseCode(String consoleInput) throws InvalidCommandException, ExecutionException,
            ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        clearAll();

        //commandHandler.updateCommandHistory(consoleInput);
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

            if(matchMethodsToRun.containsKey(getSymbol(commandStack.peek()))){ // if not actual command
                matchMethodsToRun.get(getSymbol(commandStack.peek())).run();
            } else {
                buildExecutableCommand(); // if it is command like for and repeat
            }
        }

        while(argumentStack.size()!=0){
            if(argumentStack.peek().isItExecutable()){
                //commandHandler.updateCommandHistory();
                output = (Double) argumentStack.pop().execute();
            } else{
                System.out.println("Shoudl reiterate back to the stack");
                //System.out.println("This is what it returns "+argumentStack.peek().execute());
                //List<String> codes=new ArrayList<>();
                //codes.addAll((Collection<? extends String>) argumentStack.pop().execute());
                //commandStack.addAll(codes);
                //commandStack.addAll((Collection<? extends String>) argumentStack.pop().execute());
                //commandStack.add((String) argumentStack.pop().execute());
                Object com=argumentStack.pop().execute();
                System.out.println("Added to commad stack");
                if(com instanceof String){
                    System.out.println("Name will be added "+com.toString());
                    commandStack.add((String)com);
                } else{
                    System.out.println("List will be added "+com.toString());
                    commandStack.addAll((Collection<? extends String>) com);
                }
//                for(int i=0; i<Arrays.asList(argumentStack.peek().execute()).size();i++){
//                    System.out.println(" Commands are "+Arrays.asList(argumentStack.peek().execute()).get(i));
//                    commandStack.add((String)Arrays.asList(argumentStack.peek().execute()).get(i)) ;
//                }
//                argumentStack.pop();
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

    //DELETE THIS
    public void clear()
    {
        mySymbols.clear();
    }

    /**
     * @param lang the name of the syntax source language name
     *               Adds the keys to mySymbols, thus comparison can be done
     */
    public void addPatterns(String lang, String s) {
        mySymbols.clear();

        addToResourceMap(lang);
        addToResourceMap(s);
    }

    private void addToResourceMap(String lang) {
        ResourceBundle resources = ResourceBundle.getBundle(lang);
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
       System.out.println("INVALID:"+command+  "is |" + command+  "this");
       //System.out.println("Asciii code is "+ (int) command.toCharArray());
        for (Map.Entry<String, Pattern> e : mySymbols) {
            if (match(command, e.getValue())) {
                System.out.println("The key is "+e.getKey());
                return e.getKey();
            }
        }
        // FIXME: perhaps throw an exception instead

        return ERROR;
    }

    private boolean match(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

    private int readArgumentSize(String key) {
        return Integer.parseInt(sizes.getString(key));

    }

    private void clearAll(){
        commandList.clear();
        commandStack.clear();
        argumentStack.clear();
        //userVariableHandler.getVariableMap().clear();
    }


}
