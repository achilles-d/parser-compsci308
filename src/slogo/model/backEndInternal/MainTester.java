package slogo.model.backEndInternal;
import slogo.model.CommandHandler;
import slogo.model.exceptions.ExecutionException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
    /*
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
    public static void main (String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, ExecutionException {
        // NO static methods needed!
        MainTester m = new MainTester();

        // set up the parser, which checks for matches in order given
        CommandHandlerAPI ch=new CommandHandlerAPI();
        UserVariableHandler uh=new UserVariableHandler();

        BackEndTurtle turtle= new BackEndTurtle(0);
        CommandParser lang = new CommandParser(ch, uh, turtle);


        // these are more specific, so add them first to ensure they are checked first
        lang.addPatterns("resources.languages.English", "resources.languages.Syntax");
        // general checks, added last
       // lang.addPatterns("resources.languages.Syntax");

        // try against different kinds of inputs
        //m.parseText(lang, m.examples);
        //String userInput = "sum 10 goto 50 30";
       // String userInput = "[ fd sum sum sum sum 10 20 30 5 5";
       //String userInput ="fd * greater? 5 3 100";

        //String userInput = "sum sum sum 10 50 30 40";
      //String userInput="repeat 5 [ fd fd fd sum 40 50 ] sum 20 40";
        //String userInput="make :var 50";

       // String userInput="make :x 20 make :y 40 repeat :x [ fd :x ]";
        //String userInput="make :x 4 repeat :x [ fd 100 rt 90 ]";
        //String userInput="for [ :dist 1 5 1 ] [ product :dist 3 ]";

        //String userInput ="[ :dist 10 40 10 ]";

        //String userInput = "vorw�rts 50";

  //~~~~~~~~~      String userInput="Repeat 10 [ fd :repcount rt 90 ]"; ~~~~~~~~~~~~~~~~~~~~~~~ DOESN'T WORK

       // String userInput= "fd 50 IfElse 1 [ fd 50 back 40 ] [ rt 90 bk 50 ] fd 50 bk 50 ";

        //String userInput="repeat 36 [ fd 50 rt 10 ]";
        //String userInput="fd 50 for hi jdlls - ls 3 [ fd 50 ] fd 50 - - ";

     //  String userInput="For [ :x 1 10 2 ] [ fd 50 ]";

        //String userInput="to test [ :x ] [ fd :x ]";


      // String userInput="IfElse 0 [ fd 50 ] [ IfElse 0 [ fd 100 ] [ rt 90 ] ]";

       //String userInput="atan sum sum sum 10 50 30 40";
//      // String userInput="set :length sum 50 random 250\n" +
//               "set :numspikes 18\n" +
//               "\n" +
//               "repeat :numspikes\n" +
//               "[\n" +
//               "  fd :length \n" +
//               "  rt / 360 :numspikes\n" +
//               "]\n";



//       String userInput="\n" +
//               "to dash [ ]\n" +
//               "[\n" +
//               "fd 100\n" +
//               " \n" +
//               "]\n" +
//               "\n" +
//               "dash";
String userInput="set :count 12\n" +
        "\n" +
        "to dash [ ]\n" +
        "[\n" +
        "  repeat :count \n" +
        "  [\n" +
        "    pu fd 10 pd fd 10\n" +
        "  ]\n" +
        "]\n" +
        "\n" +
        "\n" +
        "dash";
        //String userInput="cos less? sum difference 10 50 30 40";

       // String userInput="pu fd 10 pd fd 10";


        // note, this simple "algorithm" will not handle SLogo comments
        //m.parseText(lang, Arrays.asList(userInput.split(WHITESPACE)));// this prints
        lang.parseCode(userInput);

        //userInput = "fd :x";
        //lang.parseCode(userInput);

        //System.out.println(ch.getCommandHistory().get(0));


        //String fileInput =
               // m.readFileToString(MainTester.class.getClassLoader().getResource("square.logo").toExternalForm());
        // instead it will "comment out" the remainder of the program!
       // m.parseText(lang, Arrays.asList(fileInput.split(WHITESPACE)));

    }

}
