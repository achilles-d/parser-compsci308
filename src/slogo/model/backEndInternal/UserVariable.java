package slogo.model.backEndInternal;

public class UserVariable {

    private Double value;

    public void setValue(Double var) {
        this.value = var;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
