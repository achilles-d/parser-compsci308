package slogo.model.exceptions;

import java.util.ResourceBundle;

public class InvalidCommandException extends RuntimeException{

    public static final String ERROR_MESSAGE_PROP_DIR = "resources.errors.window.InvalidCommandException";

    public InvalidCommandException(String messageType, Throwable cause){
        super(ResourceBundle.getBundle(ERROR_MESSAGE_PROP_DIR).getString(messageType), cause);
    }

}
