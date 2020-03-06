package slogo.model.subparsers;

import java.util.*;
import java.util.regex.Pattern;

public class Symbol {

    private List<Map.Entry<String, Pattern>> mySymbols;

    /**
     *
     * @param language the current language we are using
     * @param syntax the syntaxes used to parse the commands
     */
    public Symbol(String language, String syntax){
        mySymbols = new ArrayList<>();
        addPatterns(language, syntax);

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


    private void addPatterns(String lang, String s) {
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

    private boolean match(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

}
