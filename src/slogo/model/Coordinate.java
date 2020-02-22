package slogo.model;

//Will implement this class later, just created so that interfaces for API design will compile

public class Coordinate {

    private double myX;
    private double myY;

    public Coordinate()
    {
        myX = 0;
        myY = 0;
    }

    public Coordinate(double xCord, double yCord )
    {
        myX = xCord;
        myY = yCord;
    }


    public double getY() {
        return myY;
    }

    public double getX() {
        return myX;
    }
}
