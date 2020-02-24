package slogo.model;

public class ExecutionException extends Exception{

    //This exception will be used to tell the user if there is something wrong with actually running the code
    //Default
    public ExecutionException(){
        super("Something went wrong while executing your commands. Please try again.");
    }

}
