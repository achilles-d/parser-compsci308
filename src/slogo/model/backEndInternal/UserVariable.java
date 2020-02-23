package slogo.model.backEndInternal;

import slogo.model.Variable;

public class UserVariable implements Variable {
    private String variable;
    private Object value;
    private String type;
    public UserVariable(){

    }
    @Override
    public void setVariable(String variable) {
        this.variable=variable;
    }

    /**
     *
     * @param value
     * @param <T> generic type of the variable
     */
    public <T> void setValue(T value){
        this.value=value;
    }

    /**
     *
     * @param type sets the type of the variable
     */
    public void setType(String type){
        this.type=type;

    }

    /**
     *
     * @return returns the name of the variable
     */
    public String getName(){
        return variable;
    }

    /**
     *
     * @return returns the type of the variable
     */
    public String getType(){
        return type;
    }

    /**
     *
     * @return the type of the value of the variable
     */
    public Object getValue(){
        return value;
    }
}
