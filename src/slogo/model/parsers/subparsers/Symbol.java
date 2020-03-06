package slogo.model.parsers.subparsers;

import java.util.*;
import java.util.regex.Pattern;

public class Symbol {

    private List<Map.Entry<String, Pattern>> mySymbols;

     //lang.addPatterns("resources.languages.English", "resources.languages.Syntax");

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
        final String ERROR = "NO MATCH";
        System.out.println("INVALID:"+command+  "is |" + command+  "this");

        for (Map.Entry<String, Pattern> e : mySymbols) {
            if (match(command, e.getValue())) {
                System.out.println("The key is "+e.getKey());
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
