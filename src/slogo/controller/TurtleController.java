package slogo.controller;


import slogo.model.turtle.Coordinate;
import slogo.view.components.ViewTurtle;
import slogo.model.turtle.BackEndTurtle;
import slogo.model.turtle.Line;

import java.util.*;

public class TurtleController {


    private Map<Integer, TurtlePair> turtlesMap;
    private Integer turtleIndexTracker;
    private String imageFile;

    public TurtleController(String image)
    {
        turtlesMap = new HashMap<>();
        imageFile = image;
        createNewTurtle(0);


    }

    public void createNewTurtle(int index) {
        ViewTurtle viewTurt = new ViewTurtle(index,imageFile);
        BackEndTurtle backTurt = new BackEndTurtle(index);
        TurtlePair turtleLink = new TurtlePair(viewTurt,backTurt);
        turtlesMap.put(index, turtleLink);
    }

    public Collection<Line> getLines(int index) {
        return Collections.unmodifiableList(turtlesMap.get(index).getBackEndTurtle().getLines());
    }

    public Coordinate getTurtlePosition(int id)
    {
        return turtlesMap.get(id).getBackEndTurtle().getPosition();
    }

    public double getHeading(int id)
    {
        return turtlesMap.get(id).getBackEndTurtle().getHeading();
    }

    public boolean getTurtleVisibility(int id)
    {
        return turtlesMap.get(id).getBackEndTurtle().getVisibility();
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
        for (Integer i : turtlesMap.keySet()) {
            viewList.add(turtlesMap.get(i).getViewTurtle());
        }
        return viewList;
    }

    public Collection<ViewTurtle> getAllActiveViewTurtles()
    {
        List<ViewTurtle> viewList = new ArrayList<>();
        for (Integer i : turtlesMap.keySet()) {
            if(turtlesMap.get(i).getViewTurtle().getActiveProperty().getValue())
                viewList.add(turtlesMap.get(i).getViewTurtle());
        }
        return viewList;
    }

    public Collection<BackEndTurtle> getAllBackEndTurtles() {

        List<BackEndTurtle> backList = new ArrayList<>();
        for (Integer i : turtlesMap.keySet()) {
            backList.add(turtlesMap.get(i).getBackEndTurtle());
        }
        return backList;
    }

    public Collection<BackEndTurtle> getAllActiveBackendTurtles(){
        List<BackEndTurtle> activeBackEndTurtles = new ArrayList<>();
        for (Integer i : turtlesMap.keySet()){
            if(turtlesMap.get(i).getBackEndTurtle().getActiveProperty().getValue()){
                activeBackEndTurtles.add(turtlesMap.get(i).getBackEndTurtle());
            }
        }
        return activeBackEndTurtles;
    }

    public int getNumberOfTurtles()
    {
        return turtlesMap.keySet().size();
    }

    public boolean containsKey(Integer i) {
        return turtlesMap.containsKey(i);
    }


}
