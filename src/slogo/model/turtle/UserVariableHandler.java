package slogo.model.turtle;

import java.util.*;

public class UserVariableHandler<T>  {

    private Map<String, UserVariable> allVariables = new HashMap<>();
    private List<String> keys =  new ArrayList<>();
    private List<String> values = new ArrayList<>();

    /**
     * Change a variables value in the max
     * @param variableName String of variable
     * @param value New value
     */
    public void setVariable(String variableName, double value)
    {
        allVariables.get(variableName).setValue(value);
    }

    /**
     * Get a user variable
     * @param variableName String of variable
     * @return UserVariable object to get double
     */
    public UserVariable getVariable(String variableName) {
        return allVariables.get(variableName);
    }

    /**
     * Create a new user variable and add it to the map.
     * @param variableName Name of variable.
     * @param variableValue Value of variable.
     */
    public void makeVariable(String variableName, Double variableValue) {
        keys.add(variableName);
        UserVariable newVar = new UserVariable();
        newVar.setValue(variableValue);
        allVariables.put(variableName, newVar);
    }

    /**
     * Remove variable
     * @param variableName Name of variable.
     */
    public void removeVariable(String variableName) {
        allVariables.remove(variableName);
        keys.remove(variableName);
    }

    /**
     * Get list of variable names
     * @return unmodifiable list of keys
     */
    public List<String> getKeys() {
        return Collections.unmodifiableList(keys);
    }

    /**
     * Get the variable map
     * @return unmodifiable map of keys and their values
     */
    public Map<String, UserVariable> getVariableMap() {
        return Collections.unmodifiableMap(allVariables);
    }

    /**
     * Get all values of variables
     * @return unmodifiable list of strings
     */
    public List<String> getValues() {
        for (Object v : allVariables.entrySet()) {
            values.add(v.toString());
        }
        return Collections.unmodifiableList(values);
    }
}
