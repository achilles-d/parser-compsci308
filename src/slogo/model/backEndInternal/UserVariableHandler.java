package slogo.model.backEndInternal;

import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class UserVariableHandler<T>  {

    private Map<String, UserVariable> allVariables = new HashMap<>();
    private List<String> keys =  new ArrayList<>();
    private List<String> values = new ArrayList<>();

    public UserVariable getVariable(String variableName) {
        return allVariables.get(variableName);
    }

    public void makeVariable(String variableName, Double variableValue) {
        keys.add(variableName);
        UserVariable newVar = new UserVariable();
        newVar.setValue(variableValue);
        allVariables.put(variableName, newVar);
    }

    public void removeVariable(String variableName) {
        allVariables.remove(variableName);
        keys.remove(variableName);
    }

    public List<String> getKeys() {
        return Collections.unmodifiableList(keys);
    }

    public Map<String, UserVariable> getVariableMap() {
        return Collections.unmodifiableMap(allVariables);
    }

    public List<String> getValues() {
        for (Object v : allVariables.entrySet()) {
            values.add(v.toString());
        }
        return Collections.unmodifiableList(values);
    }
}
