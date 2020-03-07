package slogo.model.exceptions;

import java.util.ResourceBundle;

public class ExecutionException extends RuntimeException{

    public static final String ERROR_MESSAGE_PROP_DIR = "resources.errors.ExecutionExceptionText";

    public ExecutionException(String messageType, Throwable cause){
        super(ResourceBundle.getBundle(ERROR_MESSAGE_PROP_DIR).getString(messageType), cause);
    }

    public ExecutionException(String messageType){
        super(ResourceBundle.getBundle(ERROR_MESSAGE_PROP_DIR).getString(messageType));
    }

    public ExecutionException(String message, java.lang.NoSuchMethodException e) {
        super(message,e);

    }

}
