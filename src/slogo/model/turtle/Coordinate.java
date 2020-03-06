package slogo.model.turtle;

//Will implement this class later, just created so that interfaces for API design will compile

import java.text.DecimalFormat;

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

    @Override
    public String toString()
    {
        return new DecimalFormat("#.#").format(myX) + "," + new DecimalFormat("#.#").format(myY);
    }
}
