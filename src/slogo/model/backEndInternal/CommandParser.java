package slogo.model.backEndInternal;

import slogo.model.Command;
import slogo.model.InvalidCommandException;
import slogo.model.Parser;

import java.util.*;
import java.util.regex.Pattern;

public class CommandParser implements Parser {
    // where to find resources specifically for this class
    private static final String RESOURCES_PACKAGE = CommandParser.class.getPackageName() + ".resources.languages.";
    // "types" and the regular expression patterns that recognize those types
    // note, it is a list because order matters (some patterns may be more generic)
    private List<Map.Entry<String, Pattern>> mySymbols;
    private Stack<Double> argumentStack=new Stack<>();
    private Stack<String> commandStack=new Stack<>();
    private List<String> commandFraction=new ArrayList<>();
    private Map<String, Integer> numberOfInputs=new HashMap<>();

    /**
     * Create an empty parser
     */
    public CommandParser () {

        mySymbols = new ArrayList<>();
        numberOfInputs.put("fd",1);
    }

    @Override
    public void parseCode(String consoleInput) throws InvalidCommandException {
        commandFraction.addAll(Arrays.asList(consoleInput.split(" ")));

        for (String str: commandFraction){
           if(getSymbol(str).equals("Constant")){
               argumentStack.add(Double.parseDouble(str));
           } else{
               commandStack.add(str);
           }
           if(commandStack.size()!=0 && areArgumentsEnough()){
               System.out.println(""+commandStack.pop()+":"+argumentStack.peek());
               //argumentStack.add(Double.parseDouble(str));

           }

        }

        while(commandStack!=null){
            //System.out.println(""+commandStack.pop()+":"+argumentStack.pop());

            System.out.println(""+commandStack.pop()+":"+argumentStack.peek());

        }


    }

    @Override
    public Command getCommand(String commandInput) throws InvalidCommandException {
        return null;
    }

    // check if we have enough argument for the command
    private boolean areArgumentsEnough(){


        System.out.println("Should print " + (argumentStack.size()>=numberOfInputs.get(commandStack.peek())));

        return (argumentStack.size()>=numberOfInputs.get(commandStack.peek()));

    }

    /**
     * Adds the given resource file to this language's recognized types
     */
    public void addPatterns (String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + syntax);
        for (String key : Collections.list(resources.getKeys())) {
            String regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key,
                    // THIS IS THE IMPORTANT LINE
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));

            //System.out.println("Used key "+key);
            //System.out.println("Used regex "+regex);
        }
    }

    /**
     * Returns language's type associated with the given text if one exists
     */
    public String getSymbol (String text) {
        final String ERROR = "NO MATCH";
        for (Map.Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
                //System.out.println("getSymbol method "+e.getKey());
                return e.getKey();
            }
        }
        // FIXME: perhaps throw an exception instead
        return ERROR;
    }


    // Returns true if the given text matches the given regular expression pattern
    private boolean match (String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return regex.matcher(text).matches();
    }



}
