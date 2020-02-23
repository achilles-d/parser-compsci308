package slogo.model.backEndInternal;

import slogo.model.Variable;

public class UserVariable<T> {

    private T value;

    public void setValue(T var) {
        this.value = var;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
