package slogo.model.exceptions;

import java.util.ResourceBundle;

public class ExecutionException extends RuntimeException{

    public ExecutionException(String message, Throwable cause){
        super(message, cause);
    }

    public ExecutionException(String message){
        super(message);
    }

}
