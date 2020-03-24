package slogo.controller;


import slogo.model.turtle.Coordinate;
import slogo.view.components.ViewTurtle;
import slogo.model.turtle.BackEndTurtle;
import slogo.model.turtle.Line;

import java.util.*;

/**
 * A class that controls multiple BackEndTurtles operating in the same workspace
 * Controller has an instance of this class
 * Example: call createNewTurtle to create a new turtle to be controlled in the same workspace
 * @author Achilles Dabrowski
 */
public class TurtleController {


    private Map<Integer, TurtlePair> turtlesMap;
    private String imageFile;

    /**
     * Create an instance of TurtleController
     * @param image the name of the image file to be used for the display of the turtle initially created
     *              by the class to be controlled by this class
     */
    public TurtleController(String image)
    {
        turtlesMap = new HashMap<>();
        imageFile = image;
        createNewTurtle(0);


    }

    /**
     * Create a new turtle at a specified index to be controlled
     * @param index the index of the new turtle
     */
    public void createNewTurtle(int index) {
        ViewTurtle viewTurt = new ViewTurtle(index,imageFile);
        BackEndTurtle backTurt = new BackEndTurtle(index);
        TurtlePair turtleLink = new TurtlePair(viewTurt,backTurt);
        turtlesMap.put(index, turtleLink);
    }

    /**
     * Return the list of Line objects corresponding to the lines drawn by the turtle at the specified
     * index
     * @param index the index of the turtle of interest
     * @return the list of Line objects corresponding to the lines drawn by the turtle
     */
    public Collection<Line> getLines(int index) {
        return Collections.unmodifiableList(turtlesMap.get(index).getBackEndTurtle().getLines());
    }

    /**
     * Return the Coordinate position of the turtle corresponding to the specified id
     * @param id the id of the turtle of interest
     * @return the Coordinate that represents the position of the turtle on the display window
     */
    public Coordinate getTurtlePosition(int id)
    {
        return turtlesMap.get(id).getBackEndTurtle().getPosition();
    }

    /**
     * Return the current heading of the turtle corresponding to the specified id
     * @param id the id of the turtle of interest
     * @return the heading of the turtle corresponding to the specified id
     */
    public double getHeading(int id)
    {
        return turtlesMap.get(id).getBackEndTurtle().getHeading();
    }

    /**
     * Return a boolean representing whether a turtle corresponding to the specified id is visible
     * on the turtle window
     * @param id the id of the turtle of interest
     * @return true if the turtle corresponding to the specified id is visible and false otherwise
     */
    public boolean getTurtleVisibility(int id)
    {
        return turtlesMap.get(id).getBackEndTurtle().getVisibility();
    }

    /**
     * Return the BackendTurtle object of the turtle corresponding to the specified index
     * @param index the index of the turtle of interest
     * @return the BackendTurtle object of the turtle corresponding to the specified index
     */
    public BackEndTurtle getBackEndTurtle(int index) {
        return turtlesMap.get(index).getBackEndTurtle();
    }

    /**
     * Return the ViewTurtle object of the turtle corresponding to the specified index
     * @param index the index of the turtle of interest
     * @return the ViewTurtle object of the turtle corresponding to the specified index
     */
    public ViewTurtle getViewTurtle(int index)
    {
        return turtlesMap.get(index).getViewTurtle();
    }

    /**
     * Return a Collection of ViewTurtle objects of all of the turtles being controlled
     * @return a Collection of ViewTurtle objects of all of the turtles being controlled
     */
    public Collection<ViewTurtle> getAllViewTurtles() {

        List<ViewTurtle> viewList = new ArrayList<>();
        for (Integer i : turtlesMap.keySet()) {
            viewList.add(turtlesMap.get(i).getViewTurtle());
        }
        return viewList;
    }

    /**
     * Return a Collection of ViewTurtle objects corresponding to all of the currently active turtles
     * @return a Collection of ViewTurtle objects corresponding to all of the currently active
     * turtles being controlled
     */
    public Collection<ViewTurtle> getAllActiveViewTurtles()
    {
        List<ViewTurtle> viewList = new ArrayList<>();
        for (Integer i : turtlesMap.keySet()) {
            if(turtlesMap.get(i).getViewTurtle().getActiveProperty().getValue())
                viewList.add(turtlesMap.get(i).getViewTurtle());
        }
        return viewList;
    }

    /**
     * Return a Collection of BackendTurtle objects of all of the turtles being controlled
     * @return a Collection of BackendTurtle objects of all of the turtles being controlled
     */
    public Collection<BackEndTurtle> getAllBackEndTurtles() {

        List<BackEndTurtle> backList = new ArrayList<>();
        for (Integer i : turtlesMap.keySet()) {
            backList.add(turtlesMap.get(i).getBackEndTurtle());
        }
        return backList;
    }

    /**
     * Return a Collection of BackendTurtle objects corresponding to all of the currently active
     * turtles being controlled
     * @return a Collection of BackendTurtle objects corresponding to all of the currently active
     * turtles being controlled
     */
    public Collection<BackEndTurtle> getAllActiveBackendTurtles(){
        List<BackEndTurtle> activeBackEndTurtles = new ArrayList<>();
        for (Integer i : turtlesMap.keySet()){
            if(turtlesMap.get(i).getBackEndTurtle().getActiveProperty().getValue()){
                activeBackEndTurtles.add(turtlesMap.get(i).getBackEndTurtle());
            }
        }
        return activeBackEndTurtles;
    }

    /**
     * Return the number of turtles being controlled
     * @return the number of turtles being controlled
     */
    public int getNumberOfTurtles()
    {
        return turtlesMap.keySet().size();
    }

    /**
     * Check if a turtle with index i is being controlled
     * @param i the index of the desired turtle
     * @return true if a turtle corresponding to the specified index is being controlled by this
     * object and false otherwise
     */
    public boolean containsKey(Integer i) {
        return turtlesMap.containsKey(i);
    }


}
