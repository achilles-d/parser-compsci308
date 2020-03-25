package slogo.model.parsers.subparsers;

import java.util.*;
import java.util.regex.Pattern;

public class Symbol {

    private List<Map.Entry<String, Pattern>> mySymbols;

     //lang.addPatterns("resources.languages.English", "resources.languages.Syntax");

    /**
     *
     * @param language the language used by the user
     * @param syntax the path name of the syntax properties file
     * @author abebe amare
     */
    public Symbol(String language, String syntax){
        mySymbols = new ArrayList<>();
       // addPatterns("resources.languages.English", "resources.languages.Syntax");
        addPatterns(language, syntax);

    }
    /**
     * @param command the commands as a text
     * @return the commands type
     */

    public String getSymbol(String command) {
        final String ERROR = "NO MATCH is found in the resource file";

        for (Map.Entry<String, Pattern> e : mySymbols) {
            if (match(command, e.getValue())) {
                return e.getKey();
            }
        }

        // FIXME: perhaps throw an exception instead

        return ERROR;
    }

    /**
     * @param lang the name of the syntax source language name
     *               Adds the keys to mySymbols, thus comparison can be done
     */
    private void addPatterns(String lang, String s) {
        //mySymbols.clear();
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
