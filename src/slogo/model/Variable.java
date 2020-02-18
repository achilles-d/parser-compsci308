package slogo.model;

public interface Variable {

    /**
     * This is a part of the back-end internal API. It will allow one to set the value for a variable after a command has been executed
     * @param value is the new value for the variable
     */
    public void setVariable(String value);
}
