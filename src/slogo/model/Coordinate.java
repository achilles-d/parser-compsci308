package slogo.model;

//Will implement this class later, just created so that interfaces for API design will compile

//TODO change to extend Point class
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

    public double getXVal()
    {
        return myX;
    }

    public double getYVal()
    {
        return myY;
    }


    public void setXVal(double x) {
        this.myX = x;
    }

    public void setYVal(double y) {
        this.myY = y;
    }
}
