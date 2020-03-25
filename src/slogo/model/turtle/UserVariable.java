package slogo.model.turtle;

public class UserVariable {

    private Double value;

    /**
     * Method to set value of user variable
     * @param var Value user types in
     */
    public void setValue(Double var) {
        this.value = var;
    }

    /**
     * Getter to return variable value
     * @return variable double
     */
    public Double getValue() {
        return value;
    }

    /**
     * Override toString to display user variable in front end
     * @return String of the double value
     */
    @Override
    public String toString() {
        return value.toString();
    }

}
