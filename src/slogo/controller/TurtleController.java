package slogo.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import slogo.model.Line;
import slogo.model.Turtle;
import slogo.model.backEndInternal.BackEndTurtle;
import slogo.view.ViewTurtle;

import javax.swing.text.View;
import java.util.*;

public class TurtleController {


    private Map<Integer, TurtlePair> turtlesMap;
    private Integer turtleIndexTracker;

    public TurtleController()
    {
        turtlesMap = new HashMap<>();
        createNewTurtle(0);

    }

    public void createNewTurtle(int index) {
        ViewTurtle viewTurt = new ViewTurtle(index);
        BackEndTurtle backTurt = new BackEndTurtle(index);
        TurtlePair turtleLink = new TurtlePair(viewTurt,backTurt);
        turtlesMap.put(index, turtleLink);
    }

    public Collection<Line> getLines(int index) {
        return Collections.unmodifiableList(turtlesMap.get(index).getBackEndTurtle().getLines());
    }

    public BackEndTurtle getBackEndTurtle(int index) {
        return turtlesMap.get(index).getBackEndTurtle();
    }

    public ViewTurtle getViewTurtle(int index)
    {
        return turtlesMap.get(index).getViewTurtle();
    }

    public Collection<ViewTurtle> getAllViewTurtles() {

        List<ViewTurtle> viewList = new ArrayList<>();
        for (int i = 0; i < turtlesMap.keySet().size(); i++) {
            viewList.add(turtlesMap.get(i).getViewTurtle());
        }
        return viewList;
    }

    public Collection<ViewTurtle> getAllActiveViewTurtles()
    {
        List<ViewTurtle> viewList = new ArrayList<>();
        for (int i = 0; i < turtlesMap.keySet().size(); i++) {
            if(turtlesMap.get(i).getViewTurtle().getActiveProperty().getValue())
                viewList.add(turtlesMap.get(i).getViewTurtle());
        }
        return viewList;
    }

    public Collection<BackEndTurtle> getAllBackEndTurtles() {

        List<BackEndTurtle> backList = new ArrayList<>();
        for (int i = 0; i < turtlesMap.keySet().size(); i++) {
            backList.add(turtlesMap.get(i).getBackEndTurtle());
        }
        return backList;
    }

    public int getNumberOfTurtles()
    {
        return turtlesMap.keySet().size();
    }

    public boolean containsKey(Integer i) {
        return turtlesMap.containsKey(i);
    }

}
