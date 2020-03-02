package slogo.model.backEndInternal.commands;

import java.util.List;

public class ListEnd implements Command<Object> {

    List<String> group;

    public ListEnd(List<String> group){
        this.group=group;
    }

    @Override
    public List<String> execute() {
        System.out.println(" it comes here"+group.toString());
        return group;
    }

    @Override
    public List<String> updateRawCommands() {
        return null;
    }

    @Override
    public Integer updateCounter() {
        return null;
    }
}
