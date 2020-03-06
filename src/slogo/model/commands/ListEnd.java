package slogo.model.commands;

import java.util.List;

public class ListEnd implements Command<Object> {

    List<String> group;

    public ListEnd(List<String> group){
        this.group=  group;
    }

    @Override
    public List<String> execute() {
        System.out.println(" it comes here in the command "+group.toString());
        return group;
    }

    @Override
    public boolean isItExecutable() {
        return true;
    }
}
