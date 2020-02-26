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
    private Stack<Double> argumentStack = new Stack<>();
    private Stack<String> commandStack = new Stack<>();
    private List<String> commandList = new ArrayList<>();
    private Map<String, Runnable> matchMethodsToRun;
    private CommandFactory commandFactor;
    private CommandExecutor executor;
    private Integer commandCounter = 0;
    private CommandHandlerAPI commandHandler;
    private UserVariableHandler userVariableHandler;
    private int numOfCommandsToExecute=0;

    /**
     * Create an empty parser
     */

    public CommandParser(CommandHandlerAPI commandHandler, UserVariableHandler userVariableHandler,
                         BackEndTurtle turtle) {
        this.commandHandler = commandHandler;
        this.userVariableHandler = userVariableHandler;
        mySymbols = new ArrayList<>();
        commandFactor = new CommandFactory(turtle, userVariableHandler, commandList, commandCounter);

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

        commandStack.add(commandList.get(commandCounter));
        addValidValuesToTheStack();

    }



    private void parseConstant() {
        argumentStack.add(Double.parseDouble(commandList.get(commandCounter)));
    }



    @Override
    public void parseCode(String consoleInput) throws InvalidCommandException, ExecutionException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        commandList.addAll(Arrays.asList(consoleInput.split(" ")));
        numOfCommandsToExecute=commandList.size();

        while (commandCounter < numOfCommandsToExecute) {
            if (!matchMethodsToRun.containsKey(getSymbol(commandList.get(commandCounter)))) {
                matchMethodsToRun.get("Command").run();
            } else {
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



    private void buildExecutable() throws ExecutionException, ClassNotFoundException,
            NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        if (commandStack.size() != 0 && readArgumentSize(getSymbol(commandStack.peek())) <= argumentStack.size()) {

            int l = readArgumentSize(getSymbol(commandStack.peek()));
            String currentCommandName = (commandStack.pop());

            List<String> commandWithDependency = new ArrayList<>();
            Double[] arguments = new Double[l];

            for (int j = 0; j < l; j++) { arguments[j] = argumentStack.pop(); }
            currentCommandName = findCommandDependents(currentCommandName, commandWithDependency);
            System.out.println(commandWithDependency.get(0));
            Command com = (Command) commandFactor.getCommand(commandWithDependency, arguments);
            commandWithDependency.set(0, currentCommandName);
            checkIfCommandExecutable(com);
            System.out.println("Executed Commands "+commandWithDependency.toString() + Arrays.toString(arguments));
            commandHandler.updateCommandHistory(commandWithDependency.toString() + Arrays.toString(arguments));


        }
    }

    private void checkIfCommandExecutable(Command com) throws ExecutionException {
        Double rtn = (Double) executor.executeCommand(com);

        if(!rtn.equals(Double.MAX_VALUE)){
            System.out.println("not infinity " + rtn);
            //System.out.println("Size of command Stack "+ commandStack.peek());
            argumentStack.add(rtn);

        } else {
            System.out.println(" infinity");
            commandCounter=-1;
            commandList=com.updateRawCommands();
            System.out.println("Update counter "+ com.updateCounter());
            System.out.println("Update list "+ com.updateRawCommands());
            numOfCommandsToExecute=commandList.size();

        }
    }

    private String findCommandDependents(String currentCommandName, List<String> commandWithDependency) {
        if (getSymbol(currentCommandName).equals("Variable") && !matchMethodsToRun.containsKey(getSymbol(commandStack.peek()))) {
            commandWithDependency.add(getSymbol(commandStack.peek()));
            commandWithDependency.add(currentCommandName);
            currentCommandName = commandStack.pop();

        } else {
            commandWithDependency.add(getSymbol(currentCommandName));
        }
        return currentCommandName;
    }


    private void addValidValuesToTheStack() {

        int count = 0;

        count = countImmediateValues(commandList, count);

        if (count == readArgumentSize(getSymbol(commandStack.peek()))) {
            for (int k = commandCounter + 1; k < commandCounter + 1 + count; k++) {
                if (getSymbol(commandList.get(k)).equals("Variable")) {
                    commandStack.add(commandList.get(k));
                } else {
                    argumentStack.add(Double.parseDouble(commandList.get(k)));
                }
            }
            commandCounter = commandCounter + count;
        }
    }

    private int countImmediateValues(List<String> commandFraction, int count) {
        System.out.println("Commands from the stack"+commandStack.peek());
        for (int k = commandCounter + 1; k < commandCounter + 1 + readArgumentSize(getSymbol(commandStack.peek())); k++) {
            if (getSymbol(commandFraction.get(k)).equals("Constant") || getSymbol(commandFraction.get(k)).equals(
                    "Variable")) {
                count++;
            }
        }
        return count;
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
        //System.out.println("INVALID: " + command + "SIZE: " + command.length());
        for (Map.Entry<String, Pattern> e : mySymbols) {
            if (match(command, e.getValue())) {
                return e.getKey();
            }
        }
        // FIXME: perhaps throw an exception instead
//        commandList.clear();
//        commandStack.clear();
//        argumentStack.clear();
//        commandCounter=0;
        //System.out.println("Get symbol "+command);
        return ERROR;
    }

    private boolean match(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

    private int readArgumentSize(String key) {
        //System.out.println("Key to be checked "+key);

        if(!sizes.containsKey(key)){
            commandList.clear();
            commandStack.clear();
            argumentStack.clear();
            commandCounter=0;
        }


        return Integer.parseInt(sizes.getString(key));

    }


}
