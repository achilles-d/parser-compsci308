package slogo.model;

import slogo.model.Coordinate;

import java.util.List;

public interface Line {

    /**
     * This method will return the two endpoints of a line objects.
     * This will be in the back-end external API, since the front-end needs to know where the endpoints of lines are to
     * draw them correctly on the screen
     * @return list of the starting and end point for line
     */

    public Coordinate getStart ();
    public Coordinate getEnd();

    public void createLine(Coordinate start, Coordinate end);


}

