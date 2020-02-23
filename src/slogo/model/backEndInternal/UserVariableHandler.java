package slogo.model.backEndInternal;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class UserVariableHandler<T>  {

    private ObservableMap<String, UserVariable<?>> allVariables = FXCollections.observableHashMap();
    private ObservableList<String> keys =  FXCollections.observableArrayList();

    public UserVariableHandler() {
        allVariables.addListener((MapChangeListener.Change<? extends String, ? extends UserVariable<?>> change) -> {
            boolean removed = change.wasRemoved();
            if (removed != change.wasAdded()) {
                // no put for existing key
                if (removed) {
                    keys.remove(change.getKey());
                } else {
                    keys.add(change.getKey());
                }
            }
        });
    }

    public UserVariable<?> getVariable(String variableName) {
        return allVariables.get(variableName);
    }

    public void makeVariable(String variableName, T variableValue) {
        keys.add(variableName);
        UserVariable<T> newVar = new UserVariable<>();
        newVar.setValue(variableValue);
        allVariables.put(variableName, newVar);
    }

    public void removeVariable(String variableName) {
        allVariables.remove(variableName);
        keys.remove(variableName);
    }

    public ObservableList<String> getKeys() {
        return keys;
    }

    public ObservableMap<String, UserVariable<?>> getVariableMap() {
        return allVariables;
    }
}
