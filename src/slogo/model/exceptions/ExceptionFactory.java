package slogo.model.exceptions;

import java.util.ResourceBundle;

public class ExceptionFactory {
    public static final String ERROR_MESSAGE_PROP_DIR = "resources.errors.ErrorMessages";
    static ResourceBundle errorMessages = ResourceBundle.getBundle(ERROR_MESSAGE_PROP_DIR);

    public static InvalidCommandException getInvalidCommandException(String messageType){
        return new InvalidCommandException(errorMessages.getString(messageType));
    }
}
