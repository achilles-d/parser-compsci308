package slogo.model.backEndInternal;
import slogo.model.InvalidCommandException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


/**
 * An example class to show how to use regular expressions to find programming elements in text input.
 *
 * @author Robert C. Duvall
 */
public class MainTester {
    // regular expression representing any whitespace characters (space, tab, or newline)
    public static final String WHITESPACE = "\\s+";


    // utility function that reads given file and returns its entire contents as a single string
    private String readFileToString (String inputSource) {
        try {
            // this one line is dense, hard to read, and throws exceptions so better to wrap in method
            return new String(Files.readAllBytes(Paths.get(new URI(inputSource))));
        }
        catch (URISyntaxException | IOException e) {
            // NOT ideal way to handle exception, but this is just a simple test program
            System.out.println("ERROR: Unable to read input file " + e.getMessage());
            return "";
        }
    }

    // given some text, prints results of parsing it using the given language
    private void parseText (CommandParser lang, List<String> lines) {
        for (String line : lines) {
            if (line.trim().length() > 0) {
                System.out.println(String.format("%s : %s", line, lang.getSymbol(line)));
            }
        }
        System.out.println();
    }


    /**
     * Start of the program --- just run some test examples.
     */
    public static void main (String[] args) throws InvalidCommandException {
        // NO static methods needed!
        MainTester m = new MainTester();

        // set up the parser, which checks for matches in order given
        CommandParser lang = new CommandParser();
        // these are more specific, so add them first to ensure they are checked first
        lang.addPatterns("English");
        // general checks, added last
        lang.addPatterns("Syntax");

        // try against different kinds of inputs
        //m.parseText(lang, m.examples);
        //String userInput = "sum 10 goto 50 30";
       // String userInput = "[ fd sum sum sum sum 10 20 30 5 5";
       //String userInput ="fd * greater? 5 3 100";
     //  String userInput = "sum sum sum 10 50 30 40";
        //String userInput ="[ :dist 10 40 10 ]";
      // String userInput = "fd fd fd 50";
       String userInput="minus 50 50";
        // note, this simple "algorithm" will not handle SLogo comments
        //m.parseText(lang, Arrays.asList(userInput.split(WHITESPACE)));// this prints
        lang.parseCode(userInput);


        //String fileInput =
               // m.readFileToString(MainTester.class.getClassLoader().getResource("square.logo").toExternalForm());
        // instead it will "comment out" the remainder of the program!
       // m.parseText(lang, Arrays.asList(fileInput.split(WHITESPACE)));
    }
}
