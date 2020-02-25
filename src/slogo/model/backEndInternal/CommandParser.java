package slogo.model.backEndInternal;
import slogo.model.CommandHandler;
import slogo.model.ExecutionException;
import slogo.model.backEndInternal.commands.Command;
import slogo.model.InvalidCommandException;
import slogo.model.Parser;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Pattern;

public class CommandParser implements Parser {

    private static final String RESOURCES_PACKAGE = "resources.languages.";

    private ResourceBundle sizes = ResourceBundle.getBundle(RESOURCES_PACKAGE + "ArgumentSize");

    private List<Map.Entry<String, Pattern>> mySymbols;
    private Stack<Double> argumentStack = new Stack<>();
    private Stack<String> commandStack = new Stack<>();
    private List<String> commandList = new ArrayList<>();
    private Map<String, Runnable> matchMethodsToRun;
    private CommandFactory commandFactor;
    private CommandExecutor executor;
    private int commandCounter = 0;
    private CommandHandlerAPI commandHandler;
    private UserVariableHandler  userVariableHandler;

    /**
     * Create an empty parser
     */

    public CommandParser(CommandHandlerAPI commandHandler, UserVariableHandler userVariableHandler,
                         BackEndTurtle turtle) {
        this.commandHandler=commandHandler;
        this.userVariableHandler=userVariableHandler;
        mySymbols = new ArrayList<>();
        commandFactor = new CommandFactory(turtle, userVariableHandler);
        matchMethodsToRun = new HashMap<>();
        executor = new CommandExecutor();
        matchMethodsToRun.put("Constant", this::parseConstant);
        matchMethodsToRun.put("Command", this::parseCommand);
        matchMethodsToRun.put("Variable", this::parseVariable);
        matchMethodsToRun.put("ListStart", this::parseList);
        matchMethodsToRun.put("GroupStart", this::parseGroup);
    }

    private void parseGroup() {
        System.out.println("Working");
    }

    private void parseList() {
        System.out.println("Working");
    }

    private void parseVariable() {
       // addValidVariable(commandList);

        System.out.println("Variable");


    }

    private void parseCommand() {
        System.out.println("GOT TO PARSECOMMASND");
        commandStack.add(commandList.get(commandCounter));
        addValidNumToTheStack(commandList);
    }

    private void parseConstant() {
        argumentStack.add(Double.parseDouble(commandList.get(commandCounter)));
    }

    @Override
    public void parseCode(String consoleInput) throws InvalidCommandException, ExecutionException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        commandList.addAll(Arrays.asList(consoleInput.split(" ")));


        while (commandCounter < commandList.size()) {

            System.out.println(commandList.toString());

            if (!matchMethodsToRun.containsKey(getSymbol(commandList.get(commandCounter)))) {
                matchMethodsToRun.get("Command").run();
                System.out.println("EXECUTED");
            } else {
                System.out.println(getSymbol(commandList.get(commandCounter)));
                System.out.println("EXECUTED "+commandList.get(commandCounter));
                matchMethodsToRun.get(getSymbol(commandList.get(commandCounter))).run();
            }
            buildExecutable();
            commandCounter++;
        }

        int size = commandStack.size();
        for (int i = 0; i < size; i++) {
            buildExecutable();
        }

    }

    private void buildExecutable() throws ExecutionException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if (commandStack.size() != 0 && readArgumentSize(getSymbol(commandStack.peek())) <= argumentStack.size()) {
            int l = readArgumentSize(getSymbol(commandStack.peek()));
            String currentCommandName = (commandStack.pop());
            //System.out.println("LIne 106"+ getSymbol(commandStack.peek()));
            List<String> commandWithDependency=new ArrayList<>();
            Double[] arguments = new Double[l];
            for (int j = 0; j < l; j++) {
                arguments[j] = argumentStack.pop();
            }

            if(getSymbol(currentCommandName).equals("Variable") && !matchMethodsToRun.containsKey(getSymbol(commandStack.peek()))){
                System.out.println("The first command is "+ commandStack.peek());
                commandWithDependency.add(getSymbol(commandStack.peek()));
                commandWithDependency.add(currentCommandName);
                currentCommandName=commandStack.pop();

            } else{
                commandWithDependency.add(getSymbol(currentCommandName));
            }

            Command com = (Command) commandFactor.getCommand(commandWithDependency, arguments);
            commandWithDependency.set(0,currentCommandName);
           // commandWithDependency.get(0).replaceAll("",currentCommandName);
            argumentStack.add((Double) executor.executeCommand(com));
            commandHandler.updateCommandHistory(commandWithDependency.toString() + Arrays.toString(arguments));
            System.out.println(commandWithDependency.toString() + " "+Arrays.toString(arguments));
        }
    }

    // add valid constant to the stack
    private void addValidNumToTheStack(List<String> commandFraction) {
        int count = 0;
        //System.out.println("PEEK: " + commandStack.peek());
        for (int k = commandCounter + 1; k < commandCounter + 1 + readArgumentSize(getSymbol(commandStack.peek())); k++) {
            if (getSymbol(commandFraction.get(k)).equals("Constant") || getSymbol(commandFraction.get(k)).equals(
                    "Variable") ) {
                count++;
            }
        }
        //System.out.println("COUNT = " + count);
        if (count == readArgumentSize(getSymbol(commandStack.peek()))) {
            //ArrayList<String> commandWithVariable=new ArrayList<>();
            //commandWithVariable.add(commandStack.peek());
            for (int k = commandCounter + 1; k < commandCounter + 1 + count; k++) {
                if(getSymbol(commandFraction.get(k)).equals("Variable") ){
                    commandStack.add(commandFraction.get(k));
                } else{
                    argumentStack.add(Double.parseDouble(commandFraction.get(k)));
                }
            }
            commandCounter = commandCounter + count;
        }
    }

//    private void addValidVariable(List<String> commandFraction){
//
//        if(commandFraction.get(commandCounter + 1).length()!=0 && getSymbol(commandFraction.get(
//                commandCounter + 2)).equals("Constant")){
//
//            String command=commandFraction.get(commandCounter)+" " +commandFraction.get(commandCounter+1)+" " +
//                    commandFraction.get(commandCounter+2);
//            userVariableHandler.makeVariable(commandFraction.get(commandCounter+1),
//                    Double.parseDouble(commandFraction.get(commandCounter+2)));
//
//            commandHandler.updateCommandHistory(command);
//            System.out.println(userVariableHandler.getValues());
//            argumentStack.add(Double.parseDouble(commandFraction.get(commandCounter+2)));
//            //commandStack.add()
//
//            commandCounter+=2;
//        }
//
//
//    }

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
        //System.out.println("INVALID: " + command + "SIZE: " + command.length());
        for (Map.Entry<String, Pattern> e : mySymbols) {
            if (match(command, e.getValue())) {
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
        //System.out.println("line 190 "+key);
        return Integer.parseInt(sizes.getString(key));

    }


}
