package slogo.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * A class to read command files and/or save the current command history to a file
 * Controller has an instance of this class
 * Example: use it to save the current command history displayed in the command history UI window to
 * a text file (.txt)
 */
public class CommandFileIO {

    private static final String DATEFORMAT_PATTERN = "yyyyy.MMMMM.dd GGG hh:mm aaa";
    private static final String COMMAND_HISTORY_DIR = "src/codeFiles/command_history";
    private List<String> myCommandHistory;

    /**
     * Create a new CommandFileIO object
     */
    public CommandFileIO(){
        myCommandHistory = new ArrayList<>();
    }

    /**
     * Update the current command history to reflect the most recent commands to be parsed
     * and executed
     * @param currentCommandHistory a list of String representations of each of the commands
     *                              most recently parsed and executed
     */
    public void updateCommandHistory(List<String> currentCommandHistory){
        myCommandHistory = Collections.unmodifiableList(currentCommandHistory);
    }

    /**
     * Save the current command history displayed in the command history window to a text file (.txt)
     * @throws IOException if the file cannot be written
     */
    public void saveCommandHistory() throws IOException {
        DateFormat df = new SimpleDateFormat(DATEFORMAT_PATTERN);
        String filename = COMMAND_HISTORY_DIR + df.format(new Date()) + ".txt";
        try {
            BufferedWriter commandWriter = new BufferedWriter(new FileWriter(filename));
            for (String command : myCommandHistory) {
                commandWriter.write(command);
                commandWriter.newLine();
            }
            commandWriter.close();
        }
        catch(IOException ex){
            throw ex;
        }
    }

    /**
     * Read a file containing commands to be parsed and executed
     * @param commandFile the file containing commands to be parsed and executed
     * @return a String representing the command(s) that are the equivalent to the text that would
     * have been entered into the console window for parsing and execution
     * @throws IOException if the command file cannot be read
     */
    public String readCommandFile(File commandFile) throws IOException{
        try{
            return Files.readString(commandFile.toPath());
        }
        catch(IOException ex){
            throw ex;
        }
    }
}
