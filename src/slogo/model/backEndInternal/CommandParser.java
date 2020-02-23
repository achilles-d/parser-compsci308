package slogo.model.backEndInternal;
//import slogo.model.backEndInternal.commands.Sum;

import slogo.model.backEndInternal.commands.Command;
import slogo.model.InvalidCommandException;
import slogo.model.Parser;


import java.util.*;
import java.util.regex.Pattern;

public class CommandParser implements Parser {

    private static final String RESOURCES_PACKAGE = CommandParser.class.getPackageName() + ".resources.languages.";

    private ResourceBundle sizes = ResourceBundle.getBundle(RESOURCES_PACKAGE + "ArgumentSize");
    private List<Map.Entry<String, Pattern>> mySymbols;
    private Stack<Double> argumentStack = new Stack<>();
    private Stack<String> commandStack = new Stack<>();
    private List<String> commandList = new ArrayList<>();
    private Map<String, Runnable> matchMethodsToRun;
    private int commandCounter = 0;

    /**
     * Create an empty parser
     */

    public CommandParser() {

        mySymbols = new ArrayList<>();
        matchMethodsToRun = new HashMap<>();
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
    }

    private void parseCommand() {
        commandStack.add(commandList.get(commandCounter));
        addValidNumToTheStack(commandList);
    }

    private void parseConstant() {
        argumentStack.add(Double.parseDouble(commandList.get(commandCounter)));
    }

    @Override
    public void parseCode(String consoleInput) throws InvalidCommandException {
        commandList.addAll(Arrays.asList(consoleInput.split(" ")));

        while (commandCounter < commandList.size()) {

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

    private void buildExecutable() {
        if (commandStack.size() != 0 && readArgumentSize(getSymbol(commandStack.peek())) <= argumentStack.size()) {

            double x = 0;
            int l = readArgumentSize(getSymbol(commandStack.peek()));
            //System.out.println("argument size need is "+l);
            String st = commandStack.pop() + ":";

            for (int j = 0; j < l; j++) {
                st = st + " " + argumentStack.peek();
                x += argumentStack.pop();
            }

            System.out.println("" + st);
            argumentStack.add(x);
            // System.out.println("summation is "+x);
            CommandFactory cp= new CommandFactory();
            System.out.println(cp.getCommand("Difference").execute());
//
//            try {
//
//                Class<?> c = Class.forName("slogo.model.backEndInternal.commands.Sum");
//                Constructor<?> cons = c.getDeclaredConstructor(Double.TYPE, Double.TYPE);
//                Object object = cons.newInstance(45.0, 45.6);
//                Method method = c.getDeclaredMethod("execute");
//                System.out.println("answe is " + method.invoke(object));
//            } catch (Throwable e) {
//                System.err.println(e);
//            }


        }

    }

    private void addValidNumToTheStack(List<String> commandFraction) {
        int count = 0;
        //System.out.println("C values "+commandCounter);
        for (int k = commandCounter + 1; k < commandCounter + 1 + readArgumentSize(getSymbol(commandStack.peek())); k++) {
            if (getSymbol(commandFraction.get(k)).equals("Constant")) {
                count++;
            }
        }

        //System.out.println("counting values "+count);
        if (count == readArgumentSize(getSymbol(commandStack.peek()))) {
            for (int k = commandCounter + 1; k < commandCounter + 1 + readArgumentSize(getSymbol(commandStack.peek())); k++) {
                argumentStack.add(Double.parseDouble(commandFraction.get(k)));
                //System.out.println("Added "+commandFraction.get(k));
            }
            commandCounter = commandCounter + readArgumentSize(getSymbol(commandStack.peek()));

        }


    }


    @Override
    public Command getCommand(String commandInput) throws InvalidCommandException {
        return null;
    }

    /**
     * @param syntax the name of the syntax source language name
     *               Adds the keys to mySymbols, thus comparison will be done
     */
    public void addPatterns(String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + syntax);
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
        for (Map.Entry<String, Pattern> e : mySymbols) {
            if (match(command, e.getValue())) {
                return e.getKey();
            }
        }
        // FIXME: perhaps throw an exception instead
        return ERROR;
    }

    // Returns true if the given text matches the given regular expression pattern
    private boolean match(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

    private int readArgumentSize(String key) {
        return Integer.parseInt(sizes.getString(key));

    }


}
