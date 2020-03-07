package slogo.model.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StringName implements Command {


    private String name;
    private boolean executable;
    public StringName(String name){
        executable=false;
        this.name=name;
    }
    @Override
    public Object execute() {

        List<String> nameList=new ArrayList<>();
        nameList.add(name);
        return nameList;
    }

    @Override
    public boolean isItExecutable() {
        return false;
    }
}
