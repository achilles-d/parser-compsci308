package slogo.model;

import java.util.List;

public interface VariableHandler {

    /**
     * This is a part of the external back-end API so values of variables can be acquired
     * @param variableName the name of the variable
     * @return a variable object
     */
    public Variable getVariable(String variableName);

    /**
     * Makes a new variable object with the given parameters, this is in the
     * internal back-end API, as we do not want the front-end making new variables
     * @param variableName the name of the variable
     * @param variableType the type of the variable
     * @param variableValue the value of the variable
     */
    public void makeVariable(String variableName, String variableType, String variableValue);


    /**
     * Returns an unmodifiable list of all variables present in environment currently, it is a part of the external back-end API
     * @return list of all variables' objects
     */
    public List<Variable> getAllVariables();

}
