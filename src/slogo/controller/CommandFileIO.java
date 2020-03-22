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

public class CommandFileIO {

    private static final String DATEFORMAT_PATTERN = "yyyyy.MMMMM.dd GGG hh:mm aaa";
    private static final String COMMAND_HISTORY_DIR = "src/codeFiles/command_history";
    private List<String> myCommandHistory;

    public CommandFileIO(){
        myCommandHistory = new ArrayList<>();
    }

    public void updateCommandHistory(List<String> currentCommandHistory){
        myCommandHistory = Collections.unmodifiableList(currentCommandHistory);
    }

    public void saveCommandHistory() throws IOException {
        DateFormat df = new SimpleDateFormat(DATEFORMAT_PATTERN);
        String filename = COMMAND_HISTORY_DIR + df.format(new Date()) + ".txt";
        try {
            BufferedWriter commandWriter = new BufferedWriter(new FileWriter(filename));
            for (String command : myCommandHistory) {`
                commandWriter.write(command);
                commandWriter.newLine();
            }
            commandWriter.close();
        }
        catch(IOException ex){
            throw ex;
        }
    }

    public String readCommandFile(File commandFile) throws IOException{
        try{
            return Files.readString(commandFile.toPath());
        }
        catch(IOException ex){
            throw ex;
        }
    }
}
