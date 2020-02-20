package slogo.model.backEndInternal;
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
    // some examples to test for matching
    private List<String> examples = List.of(
            "",
            "# foo",
            "foo #",
            "#",
            "fd",
            "FD",
            "forwardd",
            "equalp",
            "equal?",
            "equal??",
            "+",
            "SuM",
            "-",
            "*",
            "/",
            "%",
            "~",
            "+not",
            "not+",
            "++",
            "+*+",
            "or",
            "FOR",
            "allOrNothing",
            "all_or_nothing",
            "allOr_nothing?",
            "allOr?nothing_",
            ":allornothing",
            "PI",
            "90",
            "9.09",
            "9.0.0",
            "[",
            "]",
            "(",
            ")"
    );


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
    public static void main (String[] args) {
        // NO static methods needed!
        MainTester m = new MainTester();

        // set up the parser, which checks for matches in order given
        CommandParser lang = new CommandParser();
        // these are more specific, so add them first to ensure they are checked first
        lang.addPatterns("English");
        // general checks, added last
        lang.addPatterns("Syntax");

        // try against different kinds of inputs
        m.parseText(lang, m.examples);
        String userInput = "fd 50 rt 90 BACK :distance Left :angle";
        // note, this simple "algorithm" will not handle SLogo comments
        m.parseText(lang, Arrays.asList(userInput.split(WHITESPACE)));
        String fileInput =
                m.readFileToString(MainTester.class.getClassLoader().getResource("square.logo").toExternalForm());
        // instead it will "comment out" the remainder of the program!
        m.parseText(lang, Arrays.asList(fileInput.split(WHITESPACE)));
    }
}
