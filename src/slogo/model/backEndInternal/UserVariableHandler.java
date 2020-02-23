package slogo.model.backEndInternal;

import slogo.model.Variable;
import slogo.model.VariableHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserVariableHandler implements VariableHandler {
    Map<String, UserVariable> variableMap;

    public UserVariableHandler(){
        variableMap=new HashMap<>();
    }

    @Override
    public Variable getVariable(String variableName) {
        return variableMap.get(variableName);
    }

    @Override
    /**
     * makes a new variable with a name, value and type and store it to the map
     */
    public void makeVariable(String variableName, String variableType, String variableValue) {
        UserVariable variable=new UserVariable();
        variable.setVariable(variableName);
        variable.setType(variableType);
        variable.setValue(variableValue);
        variableMap.put(variableName, variable);

    }

    @Override
    /**
     * returns all the variables stored in the map as a list
     */
    public List<Variable> getAllVariables() {
        return variableMap.values().stream().collect(Collectors.toList());
    }
}
