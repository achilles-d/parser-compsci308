package slogo.model.exceptions;

import java.util.ResourceBundle;

public class ExceptionFactory {

    public static final String ERROR_RESOURCE_DIR = "resources.errors";
    public static final String ERROR_MESSAGE_PROP_DIR = ERROR_RESOURCE_DIR + "ErrorMessages";
    public static final String ERROR_TYPE_PROP_DIR = ERROR_RESOURCE_DIR + "ExceptionTypes";
    static ResourceBundle exceptionTypes = ResourceBundle.getBundle(ERROR_TYPE_PROP_DIR);
    static ResourceBundle errorMessages = ResourceBundle.getBundle(ERROR_MESSAGE_PROP_DIR);

    public static Exception makeException(String exType, String messageType) throws Exception{
        try {
            return (Exception) Class.forName(exceptionTypes.getString(exType)).getConstructor().newInstance(errorMessages.getString(messageType));
        }
        catch(Exception ex){
            throw ex;
        }
    }
}
