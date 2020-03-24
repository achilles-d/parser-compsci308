package slogo.model.exceptions;

import java.util.ResourceBundle;

/**
 * Never used. I did not create this class or work on it.
 */
public class NoSuchMethodException extends java.lang.NoSuchMethodException {
    public static final String ERROR_MESSAGE_PROP_DIR = "resources.errors.modelproperties.NoSuchMethodExceptionText" +
            ".properties";

    public NoSuchMethodException(String messageType){
        super(messageType);
        //super(ResourceBundle.getBundle(ERROR_MESSAGE_PROP_DIR).getString(messageType), cause);
    }

//    public InvalidCommandException(String messageType){
//        super(ResourceBundle.getBundle(ERROR_MESSAGE_PROP_DIR).getString(messageType));
//    }

}
