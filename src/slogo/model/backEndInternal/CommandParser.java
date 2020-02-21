package slogo.model.backEndInternal;

import slogo.model.backEndInternal.commands.Command;
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


    /**
     * Create an empty parser
     */
    public CommandParser () {
        mySymbols = new ArrayList<>();
    }

    @Override
    public void parseCode(String consoleInput) throws InvalidCommandException {

    }

    @Override
    public Command getCommand(String commandInput) throws InvalidCommandException {
        return null;
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
        }
    }

    /**
     * Returns language's type associated with the given text if one exists
     */
    public String getSymbol (String text) {
        final String ERROR = "NO MATCH";
        for (Map.Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
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
