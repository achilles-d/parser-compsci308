package slogo.controller;

import javafx.beans.property.Property;
import slogo.model.backEndInternal.BackEndTurtle;
import slogo.view.ViewTurtle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurtleController {


    private Map<Integer, Map<BackEndTurtle, ViewTurtle>> turtlesMap = new HashMap<>();
    private Integer turtleIndexTracker = 0;

    public void createNewTurtle(Property turtleImage) {
        Map<BackEndTurtle, ViewTurtle> newTurtleLink = new HashMap<>();
        BackEndTurtle newBackEndTurtle = new BackEndTurtle();
        ViewTurtle newViewTurtle = new ViewTurtle(turtleImage);
        newTurtleLink.put(newBackEndTurtle, newViewTurtle);
        turtlesMap.put(turtleIndexTracker, newTurtleLink);
        turtleIndexTracker++;
    }

    public Map<BackEndTurtle, ViewTurtle> getTurtle(Integer index) {
        return turtlesMap.get(index);
    }

    public List<Map<BackEndTurtle, ViewTurtle>> getAllTurtles() {
        List<Map<BackEndTurtle, ViewTurtle>> allTurtles = new ArrayList<>();

        for (int i = 0; i < turtleIndexTracker; i++) {
            allTurtles.add(turtlesMap.get(i));
        }
        return allTurtles;
    }

}
