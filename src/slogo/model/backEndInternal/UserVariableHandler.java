package slogo.model.backEndInternal;

import java.util.HashMap;
import java.util.Map;
import slogo.model.Variable;
import slogo.model.VariableHandler;

import java.util.List;

public class UserVariableHandler<T>  {

    private Map<String, UserVariable<?>> allVariables = new HashMap<>();

    public UserVariable<?> getVariable(String variableName) {
        return allVariables.get(variableName);
    }


    public void makeVariable(String variableName, T variableValue) {
        UserVariable<T> newVar = new UserVariable<>();
        newVar.setValue(variableValue);
        allVariables.put(variableName, newVar);
    }


    public Map<String, UserVariable<?>> getAllVariables() {
        return allVariables;
    }
}
