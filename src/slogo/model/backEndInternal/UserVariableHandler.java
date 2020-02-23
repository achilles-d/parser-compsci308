package slogo.model.backEndInternal;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import slogo.model.Variable;
import slogo.model.VariableHandler;

import java.util.List;

public class UserVariableHandler<T>  {

    private ObservableMap<String, UserVariable<?>> allVariables = FXCollections.observableHashMap();

    UserVariableHandler() {
        
    }

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
