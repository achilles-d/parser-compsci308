package slogo.model.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StringName implements Command {


    private String name;
    private boolean executable;

    /**
     * command constructor
     * @param name string name
     */
    public StringName(String name){
        executable=false;
        this.name=name;
    }

    /**
     * Execution logic
     * @return Double value argument
     */
    @Override
    public Object execute() {

        List<String> nameList=new ArrayList<>();
        nameList.add(name);
        return nameList;
    }

    /**
     * Check if executable
     * @return Is it an executable command or not.
     */
    @Override
    public boolean isItExecutable() {
        return false;
    }
}
