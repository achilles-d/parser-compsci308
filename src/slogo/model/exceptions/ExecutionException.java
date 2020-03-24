package slogo.model.exceptions;

import java.util.ResourceBundle;

/**
 * An Exception thrown when the program misbehaves during the execution of parsed commands.
 * Example: throw a new instance of this class with messageType commandFile and cause IOException
 * when the command text file cannot be read
 * @author Achilles Dabrowski
 */
public class ExecutionException extends RuntimeException{

    public static final String ERROR_MESSAGE_PROP_DIR = "resources.errors.ExecutionExceptionText";

    /**
     * Create a new instance of ExecutionException with a defined cause and message.
     * @param messageType the type of error message to be set. This String should exactly match
     *                    a key in the ExecutionExceptionText resource file.
     * @param cause a Throwable that caused the this instance of ExecutionException to be thrown
     */
    public ExecutionException(String messageType, Throwable cause){
        super(ResourceBundle.getBundle(ERROR_MESSAGE_PROP_DIR).getString(messageType), cause);
    }

    /**
     * Create a new instance of ExecutionException with a defined message.
     * @param messageType the type of error message to be set. This String should exactly match
     *        a key in the ExecutionExceptionText resource file.
     */
    public ExecutionException(String messageType){
        super(ResourceBundle.getBundle(ERROR_MESSAGE_PROP_DIR).getString(messageType));
    }

    /**
     * Create a new instance of ExecutionException with a defined message when the Exception is
     * caused by a NoSuchMethodException
     * @param message the type of error message to be set. This String should exactly match
     *        a key in the ExecutionExceptionText resource file.
     * @param e a NoSuchMethodException that is the cause of the creation of this instance of
     *          ExecutionException
     */
    public ExecutionException(String message, java.lang.NoSuchMethodException e) {
        super(message,e);

    }

}
