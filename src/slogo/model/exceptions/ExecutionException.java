package slogo.model.exceptions;

import java.util.ResourceBundle;

public class ExecutionException extends Exception{

    public ExecutionException(){
        super("Something went wrong while executing your commands. Please try again.");
    }

}
