package slogo.model.exceptions;

//This exception will let the user know if a command they entered is invalid
public class InvalidCommandException extends RuntimeException{

    //Default
    public InvalidCommandException(String message){
        super(message);
    }

}
