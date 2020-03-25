package slogo.model.exceptions;

import java.lang.module.ResolutionException;
import java.util.ResourceBundle;

/**
 * An Exception thrown when program tries to parse an invalid command.
 * Example: throw a new instance with a messageType of classNotFound and a cause of ClassNotFoundException
 * when the command entered causes reflection to look for a class that doesn't exist
 * @author Achilles Dabrowski
 */
public class InvalidCommandException extends RuntimeException{


    public static final String ERROR_MESSAGE_PROP_DIR = "resources.errors.InvalidCommandExceptionText";

    /**
     * Create a new instance of InvalidCommandException with a defined cause and message.
     * @param messageType the type of error message to be set. This String should exactly match
     *                    a key in the InvalidCommandExceptionText resource file.
     * @param cause a Throwable that caused the this instance of InvalidCommandException to be
     *              thrown
     */
    public InvalidCommandException(String messageType, Throwable cause){
        super(ResourceBundle.getBundle(ERROR_MESSAGE_PROP_DIR).getString(messageType), cause);
    }

    /**
     * Create a new instance of InvalidCommandException with a defined message.
     * @param messageType the type of error message to be set. This String should exactly match
     *        a key in the InvalidCommandExceptionText resource file.
     */
    public InvalidCommandException(String messageType){
        super(ResourceBundle.getBundle(ERROR_MESSAGE_PROP_DIR).getString(messageType));
    }

    /**
     * Create a new instance of InvalidCommandException with a defined message when it is
     * caused by a NoSuchMethodException
     * @param message the type of error message to be set. This String should exactly match
     *        a key in the InvalidCommandExceptionText resource file.
     * @param e a NoSuchMethodException that is the cause of the creation of this instance of
     *          InvalidCommandException
     */
    public InvalidCommandException(String message, java.lang.NoSuchMethodException e) {
        super(message,e);

    }
}
